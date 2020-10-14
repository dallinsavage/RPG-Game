  
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
		System.out.println("You feel well rested, Hp restored");
	}
	public Circle draw(int x, int y) {
		setXValue(x);
		setYValue(y);
		Circle player = new Circle(x, y, 20);
		player.setFill(Color.BLUE);
		player.setStroke(Color.BLUE);
		return player;
	}
}
