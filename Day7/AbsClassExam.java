package Day7;
abstract class Area {
	public abstract void draw();
}

class Rect extends Area {
	@Override
	public void draw() {
		System.out.println("Rect");
	}
}

class Circle extends Area {
	@Override
	public void draw() {
		System.out.println("Circle");
	}
}



class Tri extends Area {

	@Override
	public void draw() {
			System.out.println("Tri");
		}
	
}

public class AbsClassExam {
	public static void main(String[] args) {
		Tri tri = new Tri();
		tri.draw();
		
		Rect rect = new Rect();
		rect.draw();
		
		Circle circle = new Circle();
		circle.draw();
	}
}
