import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public abstract class Enemy extends Character {
	
	Enemy() {
	}
	Enemy(int newEndurance, int newArmor, int newDamage, int newAttackBonus) {
		super(newEndurance, newArmor, newDamage, newAttackBonus);
	}
	public void animate(Pane pane) {
		PathTransition pt = new PathTransition();
		pt.setPath(new Line(this.getX(), this.getY(), this.getX() - 50, this.getY()));
		pt.setAutoReverse(true);
		pt.setCycleCount(2);
		pt.setDuration(Duration.millis(500));
		int index = pane.getChildren().indexOf(this);
		pt.setNode(pane.getChildren().get(index));
		pt.play();
	}
}


class Skeleton extends Enemy {
	Skeleton() {
		super(3, 3, 3, 4);
		setExp(10);
		setGold((int)(Math.random() * 10));
	}
	public String stringHp() {
		return "hp = " + getHp();
	}
	public String getName() {
		return "Skeleton";
	}
	public Circle draw(int x, int y) {
		setX(x);
		setY(y);
		Circle skeleton = new Circle(x, y, 20);
		skeleton.setFill(Color.WHITE);
		skeleton.setStroke(Color.BLACK);
		return skeleton;
	}
}

class Zombie extends Enemy {
	Zombie() {
		super(2, 2, 5, 3);
		setExp(10);
		setGold((int)(Math.random() * 10));
	}
	public String stringHp() {
		return "hp = " + getHp();
	}
	public String getName() {
		return "Zombie";
	}
	public Circle draw(int x, int y) {
		setX(x);
		setY(y);
		Circle zombie = new Circle(x, y, 20);
		zombie.setFill(Color.GREEN);
		zombie.setStroke(Color.GREEN);
		return zombie;
	}
}
	
class Tank extends Enemy {
	Tank() {
		super(5, 4, 1, 3);
		setExp(15);
		setGold((int)(Math.random() * 20));
	}
	public String stringHp() {
		return "hp = " + getHp();
	}
	public String getName() {
		return "Tank";
	}
	public Circle draw(int x, int y) {
		setX(x);
		setY(y);
		Circle tank = new Circle(x, y, 30);
		tank.setFill(Color.YELLOW);
		tank.setStroke(Color.YELLOW);
		return tank;
	}
}
class Boss extends Enemy {
	Boss() {
		super(10, 4, 5, 3);
		setExp(150);
		setGold((int)(Math.random() * 100) + 50);
	}
	public String stringHp() {
		return "hp = " + getHp();
	}
	public String getName() {
		return "Boss";
	}
	public Circle draw(int x, int y) {
		setX(x);
		setY(y);
		Circle boss = new Circle(x, y, 30);
		boss.setFill(Color.RED);
		boss.setStroke(Color.RED);
		return boss;
	}
}
