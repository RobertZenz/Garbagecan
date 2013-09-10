#include <cairo/cairo.h>
#include <math.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <time.h>
#include <X11/Xlib.h>

#define PI 3.14159265f

float GetX(float degree, int diameter) {
	return diameter * cosf(degree * PI / 180);
}

float GetY(float degree, int diameter) {
	return diameter * sinf(degree * PI / 180);
}

void DrawLineFromCenter(Display* dpy, Drawable d, GC gc, int centerX,
		int centerY, int innerRadius, int outerRadius, float degrees) {

	degrees += 90;

	int x1 = (int) GetX(degrees, outerRadius) + centerX;
	int y1 = (int) GetY(degrees, outerRadius) + centerY;
	int x2 = (int) GetX(degrees, innerRadius) + centerX;
	int y2 = (int) GetY(degrees, innerRadius) + centerY;

	XDrawLine(dpy, d, gc, x1, y1, x2, y2);
}

int main(int argv, char* argc[]) {
	Display *dpy = XOpenDisplay(getenv("DISPLAY"));
	int scr = DefaultScreen(dpy);
	Window root = RootWindow(dpy, scr) ;
	Window w = XCreateSimpleWindow(dpy, root, 64, 64, 256, 256,
			BlackPixel(dpy, scr) , WhitePixel(dpy, scr), 0);
	XSelectInput(dpy, w, ExposureMask | KeyPressMask);
	XMapWindow(dpy, w);

	GC gc = XCreateGC(dpy, w, 0, NULL );
	XSetLineAttributes(dpy, gc, 2, LineSolid, CapNotLast, JoinMiter);

	XWindowAttributes wa;

	XGetWindowAttributes(dpy, w, &wa);

	Pixmap double_buffer = XCreatePixmap(dpy, root, wa.width, wa.height,
			wa.depth);

	int oldWidth = wa.width;
	int oldHeight = wa.height;

	time_t timestamp;
	struct tm localtimestamp;
	struct timespec sleep;
	sleep.tv_nsec = 15000000L;
	sleep.tv_sec = 0;
	struct timespec sleepremaining;

	while (1) {
		timestamp = time(NULL );
		localtimestamp = *localtime(&timestamp);

		// Update the window attributes
		XGetWindowAttributes(dpy, w, &wa);

		if (wa.width != oldWidth || wa.height || oldHeight) {
			double_buffer = XCreatePixmap(dpy, root, wa.width, wa.height,
					wa.depth);
		}

		oldWidth = wa.width;
		oldHeight = wa.height;

		// Reset background
		XSetBackground(dpy, gc, 0x000000);
		XSetForeground(dpy, gc, 0x000000);
		XFillRectangle(dpy, double_buffer, gc, 0, 0, wa.width, wa.height);

		XSetForeground(dpy, gc, 0xFFFFFF);

		int centerX = wa.width / 2;
		int centerY = wa.height / 2;

		int count = 0;
		for (count = 0; count < 24; count++) {
			float degrees = 360 / 23 * count;
			DrawLineFromCenter(dpy, double_buffer, gc, centerX, centerY,
					(int) (fmin(centerX, centerY) * 0.7),
					(int) (fmin(centerX, centerY) * 0.9), degrees);
		}

		// Hours hand
		XSetForeground(dpy, gc, 0x0000FF);
		DrawLineFromCenter(dpy, double_buffer, gc, centerX, centerY, 0,
				(int) (fmin(centerX, centerY) * 0.55),
				localtimestamp.tm_hour * 360 / 23);

		// Minutes hand
		XSetForeground(dpy, gc, 0x00FF00);
		DrawLineFromCenter(dpy, double_buffer, gc, centerX, centerY, 0,
				(int) (fmin(centerX, centerY) * 0.65),
				localtimestamp.tm_min * 360 / 59);

		// Seconds hand
		XSetForeground(dpy, gc, 0xFF0000);
		DrawLineFromCenter(dpy, double_buffer, gc, centerX, centerY, 0,
				(int) (fmin(centerX, centerY) * 0.75),
				localtimestamp.tm_sec * 360 / 59);

		XCopyArea(dpy, double_buffer, w, gc, 0, 0, wa.width, wa.height, 0, 0);
		XFlush(dpy);

		nanosleep(&sleep, &sleepremaining);
	}

	return EXIT_SUCCESS;
}
