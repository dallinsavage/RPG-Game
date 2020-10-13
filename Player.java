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
	public Circle drawPlayer() {
		Circle player = new Circle(100, 250, 20);
		player.setFill(Color.BLUE);
		player.setStroke(Color.BLUE);
		return player;
	}
	public Player spawnPlayer() {
		
		int playerEndurance = 1;
		int playerArmor = 0;
		int playerDamage = 1;
		int playerAttackBonus = 0;
		int points = 25;
		return null;
	}
	
}
