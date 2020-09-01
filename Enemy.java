public abstract class Enemy extends Character {
	Enemy() {
	}
	Enemy(int newEndurance, int newArmor, int newDamage, int newAttackBonus) {
		super(newEndurance, newArmor, newDamage, newAttackBonus);
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
}