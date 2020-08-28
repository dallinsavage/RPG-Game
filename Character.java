import java.util.Scanner;

public class Character {
	private double hp;
	private int armor;
	private int damage;
	
	Character() {
	}
	Character(double newHp, int newArmor, int newDamage) {
		hp = newHp;
		armor = newArmor;
		damage = newDamage;
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
	public void doDamage(Character target) {
		double targetHp = target.getHp();
		int targetArmor = target.getArmor();
		double damageTaken = damage - targetArmor;
		target.setHp(targetHp - damageTaken);
	}
	public String toString() {
		return "hp = " + hp;
	}
	
}

	class Player extends Character {
		Player() {
		}
		Player(double newHp, int newArmor, int newDamage) {
			super(newHp, newArmor, newDamage);
		}
	}
	
	class Enemy extends Character {
		Enemy() {
		}
		Enemy(double newHp, int newArmor, int newDamage) {
			super(newHp, newArmor, newDamage);
		}
	}
	
	class testCharacter {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		
		//spawn the player
		
		System.out.println("Enter player hp, armor, and damage");
		int playerHp = input.nextInt();
		int playerArmor = input.nextInt();
		int playerDamage = input.nextInt();
		Player player1 = new Player(playerHp, playerArmor, playerDamage);
		while (player1.getHp() >= 0) {
			int score = 0;
			
			//spawn enemies
			
			System.out.println("How many Enemies do you want to spawn");
			int numberOfEnemies = input.nextInt();
			Enemy[] enemies = new Enemy[numberOfEnemies];
			
		}
	}
}