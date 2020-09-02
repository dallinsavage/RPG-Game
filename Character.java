import java.util.Scanner;

public abstract class Character {
	private int level = 1;
	private int exp;
	private int hp;
	private int maxHp;
	private int armor;
	private int damage;
	private int attackBonus;
	private int endurance;
	private int gold;
	Character() {
	}
	Character(int newEndurance, int newArmor, int newDamage, int newAttackBonus) {
		endurance = newEndurance;
		maxHp = endurance * 10;
		hp = newEndurance * 10;
		armor = newArmor;
		damage = newDamage;
		attackBonus = newAttackBonus;
	}
	public void setHp(int newHp) {
		hp = newHp;
	}
	public void setMaxHp() {
		maxHp = getEndurance() * 10;
	}
	public int getHp() {
		return hp;
	}
	public int getMaxHp() {
		return maxHp;
	}
	public int getEndurance() {
		return endurance;
	}
	public int getArmor() {
		return armor;
	}
	public int getDamage() {
		return damage;
	}
	public int getAttackBonus() {
		return attackBonus;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int newExp) {
		exp = newExp;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int newGold) {
		gold = newGold;
	}
	public boolean canLevelUp() {
		int expNeeded = 50 * getLevel();
		if (exp - expNeeded >= 0) {
			return true;
		}
		else return false;
	}
	public int getLevel() {
		return level;
	}
	public void levelUp() {
		int points = 1;
		Scanner input = new Scanner(System.in);
		System.out.println("Congratulations, you leveled up");
		System.out.println("Destribute your " + points + " point");
		System.out.println("1: Endurance");
		System.out.println("2: Armor");
		System.out.println("3: Damage");
		System.out.println("4: Attack bonus");
		int pointSelect = input.nextInt();
		for (points = 1; points > 0; points--) {
		switch (pointSelect) {
		case 1:	endurance = endurance + 1; break;
		case 2: armor = armor + 1; break;
		case 3: damage = damage + 1; break;
		case 4: attackBonus = attackBonus + 1; break;
		default: System.out.println("Enter a valid selection"); break;
		}
		}
		level = level + 1;
		exp = exp - (50 * level);
		maxHp = endurance * 10;
	}
	public void doDamage(Character target) {
		int attack = (int)((Math.random() * 10) + (Math.random() * 10)) ;
		int targetHp = target.getHp();
		int targetArmor = target.getArmor();
		if (attack + attackBonus > targetArmor) {
			target.setHp(targetHp - damage);
			System.out.println(getName() + (" hit ") + target.getName() + " for " + damage + " damage");
		}
		else {
			System.out.println(getName() + " missed " + target.getName());
		}
	}
	public abstract String getName();
	public abstract String stringHp();
}
