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
		System.out.println("You feel well rested");
	}
}