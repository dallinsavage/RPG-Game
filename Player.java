import javafx.animation.PathTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Player extends Character {
	Player() {
		setExp(0);
		setGold(0);
	}
	Player(int newEndurance, int newArmor, int newDamage, int newAttackBonus) {
		super(newEndurance, newArmor, newDamage, newAttackBonus);
		setExp(0);
		setGold(0);
	}
	public String getName() {
		return "Player";
	}
	public String stringHp() {
		return "hp = " + getHp();
	}
	public void rest() {
		setHp(getMaxHp());
	}
	public Circle draw(int x, int y) {
		setX(x);
		setY(y);
		Circle player = new Circle(x, y, 20);
		player.setFill(Color.BLUE);
		player.setStroke(Color.BLUE);
		return player;
	}
	@Override
	public PathTransition animate(Pane pane) {
		Line line = new Line(this.getX(), this.getY(), this.getX() + 50, this.getY());
		line.setOpacity(0);
		Circle circle = this.draw(this.getX(), this.getY());
		pane.getChildren().addAll(line, circle);
		PathTransition pt = new PathTransition(Duration.millis(500), line, circle);
		pane.getChildren().remove(this);
		pt.setAutoReverse(true);
		pt.setCycleCount(2);
		return pt;
	}
}
