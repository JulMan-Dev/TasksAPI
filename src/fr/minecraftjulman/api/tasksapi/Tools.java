package fr.minecraftjulman.api.tasksapi;

class Tools {
	public static boolean classImplements___(Class<?> class_, Class<?> interface_) {
		for (Class<?> ci : class_.getInterfaces()) {
			if (ci.equals(interface_)) return true;
		}
		
		return false;
	}
}
