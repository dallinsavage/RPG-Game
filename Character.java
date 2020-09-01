import java.util.ArrayList;
import java.util.Scanner;

public abstract class Character {
	private double hp;
	private int armor;
	private int damage;
	private int attackBonus;
	
	Character() {
	}
	Character(double newHp, int newArmor, int newDamage, int newAttackBonus) {
		hp = newHp;
		armor = newArmor;
		damage = newDamage;
		attackBonus = newAttackBonus;
	}
	public void setHp(double newHp) {
		hp = newHp;
	}
	public double getHp() {
		return hp;
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
	public void doDamage(Character target) {
		double attack = (Math.random() * 10) + (Math.random() * 10) ;
		double targetHp = target.getHp();
		double targetArmor = target.getArmor();
		if (attack + attackBonus > targetArmor) {
			target.setHp(targetHp - damage);
			System.out.println(getName() + (" hit"));
		}
		else {
			System.out.println(getName() + " miss");
		}
	}
	public abstract String getName();
	public abstract String stringHp();
}

	class Player extends Character {
		Player() {
		}
		Player(double newHp, int newArmor, int newDamage, int newAttackBonus) {
			super(newHp, newArmor, newDamage, newAttackBonus);
		}
		public String getName() {
			return "Player";
		}
		public String stringHp() {
			return "hp = " + getHp();
		}
	}
	
	abstract class Enemy extends Character {
		Enemy() {
		}
		Enemy(double newHp, int newArmor, int newDamage, int newAttackBonus) {
			super(newHp, newArmor, newDamage, newAttackBonus);
		}
	}
	
	class Skeleton extends Enemy {
		Skeleton() {
			super(20, 10, 5, 2);
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
			super(30, 5, 10, 0);
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
			super(50, 12, 2, 4);
		}
		public String stringHp() {
			return "hp = " + getHp();
		}
		public String getName() {
			return "Tank";
		}
	}
	
	class testCharacter {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		
		//spawn the player
		
		System.out.println("Enter player hp, armor between(0 and 19), damage, and attack bonus");
		int playerHp = input.nextInt();
		int playerArmor = input.nextInt();
		int playerDamage = input.nextInt();
		int playerAttackBonus = input.nextInt();
		Player player1 = new Player(playerHp, playerArmor, playerDamage, playerAttackBonus);
		int score = 0;
		boolean alive = true;
		while (alive) {
			
			//spawn enemies
			
			System.out.println("How many Enemies do you want to spawn");
			int numberOfEnemies = input.nextInt();
			ArrayList<Character> enemies = new ArrayList<Character>();
			for (int i = 0; i < numberOfEnemies; i++) {
				System.out.println("What do you want to spawn");
				System.out.println("1: Skeleton");
				System.out.println("2: Zombie");
				System.out.println("3: Tank");
				int select = input.nextInt();
				if (select == 1) {
					enemies.add(new Skeleton());
				}
				else if (select == 2) {
					enemies.add(new Zombie());
				}
				else if (select == 3) {
					enemies.add(new Tank());
				}
				else {
					System.out.println("enter a valid selection");
					i--;
				}

			}
			
			// run combat
			
			while (!enemies.isEmpty()) {
				if (player1.getHp() > 0) {
					System.out.println("Choose target between 0 and " + (enemies.size() - 1));
					int target = input.nextInt();
					player1.doDamage(enemies.get(target));
					for (int z = 0; z < enemies.size(); z++) {
						if (enemies.get(z).getHp() <= 0) {
							System.out.println(enemies.get(z).getName() + " has been slain");
							enemies.remove(z);
							score++;
							z--;
						}
					}
					for (int x = 0; x < enemies.size(); x++) {
						enemies.get(x).doDamage(player1);
					}
					System.out.println(player1.getName() + " " + player1.stringHp());
					for (int y = 0; y < enemies.size(); y++) {
						System.out.println(enemies.get(y).getName() + " " + enemies.get(y).stringHp());
					}
				}
				else {
					System.out.println("You died");
					System.out.println("Final score = " + score);
					alive = false;
					break;
					
				}
			}
		}
	}
}
