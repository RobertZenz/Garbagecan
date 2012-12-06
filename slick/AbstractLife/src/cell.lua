-- cellLiving
-- livingDirectNeighbors
-- livingIndirectNeighbors
function isLiving(cl, ldn, lin)
	if ldn == 2 and (lin >= 3 or lin <= 4) then
		return true;
	end

	return false;
end
