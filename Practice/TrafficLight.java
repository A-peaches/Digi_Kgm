package Practice;

public enum TrafficLight {
	RED("Stop"),
	YELLOW("Caution"),
	GREEN("Go");
	
	private final String action;
	
	TrafficLight(String action) {
		this.action = action;
	}
	
	public String getAction() {
		return this.action;
	}
	
	public static String getActionByColor(String color) {
		for(TrafficLight light : TrafficLight.values()) {
			if(light.name().equalsIgnoreCase(color)) {
				return light.getAction();
			}
		}
		return "UnkNown color";
	}
}
