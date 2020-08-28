package fr.minecraftjulman.api.tasksapi;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * <br>
 * <br>
 * <b>I'm not affilied with {@link Bukkit}, thanks !</b>
 * <br>
 * 
 * @author MinecraftJulMan
 */
public class TasksManager {
	@SuppressWarnings("unused")
	private Class<? extends JavaPlugin> plugin;
	private HashMap<String, Consumer<String>> tasks = new HashMap<String, Consumer<String>>();

	/**
	 * 
	 * @param plugin The Bukkit plugin main class.
	 * @throws RuntimeException Only if "plugin" do not implement {@link ImTaskManager}
	 * @author MinecraftJulMan
	 */
	public TasksManager(Class<? extends JavaPlugin> plugin) throws Exception {
		if (!(Tools.classImplements___(plugin, ImTaskManager.class))) {
			throw new RuntimeException("\"plugin\" must implement ImTaskManager !");
		}
		
		this.plugin = plugin;
	}
	
	/**
	 * 
	 * @param name The task name, with this you can execute the task (executeTask(name)).
	 * @param function The function will execute when call task. (String = task name)
	 * @author MinecraftJulMan
	 */
	public void createTask(@Nonnull String name, @Nonnull Consumer<String> function) {
		tasks.put(name, function);
	}
	
	
	/**
	 * 
	 * @param name The task name to execute.
	 * @throws NullPointerException If "name" is null or if the task do not exist.
	 * @author MinecraftJulMan
	 */
	public void executeTask(@Nonnull String name) {
		String name_not_null = Objects.requireNonNull(name);
		
		if (tasks.containsKey(name_not_null)) {
			tasks.get(name_not_null).accept(name_not_null);
		} else {
			throw new NullPointerException("Task with name \"" + name_not_null + "\" not found ! :(");
		}
	}
	
	/**
	 * 
	 * @param name The task name to remove
	 * @throws NullPointerException If "name" is null or if the task do not exist.
	 * @author MinecraftJulMan
	 */
	public void removeTask(@Nonnull String name) {
		String name_ = Objects.requireNonNull(name);
		
		if (tasks.containsKey(name_)) {
			tasks.remove(name_);
		} else {
			throw new NullPointerException("Task with name \"" + name_ + "\" not found ! :(");
		}
	}
}
