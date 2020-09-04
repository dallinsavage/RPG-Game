import java.util.ArrayList;
import java.util.Scanner;

public class Run_Game {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		
		//spawn the player
		
		int playerEndurance = 1;
		int playerArmor = 0;
		int playerDamage = 1;
		int playerAttackBonus = 0;
		int points = 25;
		while (points > 0) {
			System.out.println("Destribute your " + points + " points");
			System.out.println("Enter a category");
			System.out.println("1: Endurance");
			System.out.println("2: Armor");
			System.out.println("3: Damage");
			System.out.println("4: Attack bonus");
			int pointSelect = input.nextInt();
			System.out.println("How many points to you want in this catagorie");
			int amount = input.nextInt();
			if (amount > points) {
				System.out.println("you dont have that many points left");
			}
			else {
			switch (pointSelect) {
			case 1: playerEndurance = playerEndurance + amount; points = points - amount; break;
			case 2:	playerArmor = playerArmor + amount; points = points - amount; break;
			case 3:	playerDamage = playerDamage + amount; points = points - amount; break;
			case 4: playerAttackBonus = playerAttackBonus + amount; points = points - amount; break;
			default: System.out.println("Enter a valid selection"); break;
			}
			}
		}
		Player player1 = new Player(playerEndurance, playerArmor, playerDamage, playerAttackBonus);
		int score = 0;
		boolean alive = true;
		while (alive) {
			if (player1.canLevelUp()) {
				player1.levelUp();
			}
			System.out.println("What would you like to do");
			System.out.println("1: Continue adventure");
			System.out.println("2: Rest");
			System.out.println("3: Enter shop");
			int doNext = input.nextInt();
			if (doNext == 1) {
			
			//spawn enemies

			System.out.println("How many Enemies do you want to spawn");
			int numberOfEnemies = input.nextInt();
			ArrayList<Character> enemies = new ArrayList<Character>();
			for (int i = 0; i < numberOfEnemies; i++) {
				System.out.println("What do you want to spawn");
				System.out.println("1: Skeleton");
				System.out.println("2: Zombie");
				System.out.println("3: Tank");
				System.out.println("4: Boss");
				int select = input.nextInt();
				switch (select) {
				case 1: enemies.add(new Skeleton()); break;
				case 2: enemies.add(new Zombie()); break;
				case 3:	enemies.add(new Tank()); break;
				case 4:	enemies.add(new Boss()); break;
				default: System.out.println("enter a valid selection"); i--;
				}

			}
			
			// run combat
			
			while (!enemies.isEmpty()) {
				if (player1.getHp() > 0) {
					System.out.println("Choose target between 0 and " + (enemies.size() - 1));
					int target = input.nextInt();
					if (target > enemies.size() - 1) {
						System.out.println("Enter a valid target");
					}
					else {
					player1.doDamage(enemies.get(target));
					for (int z = 0; z < enemies.size(); z++) {
						if (enemies.get(z).getHp() <= 0) {
							System.out.println(enemies.get(z).getName() + " has been slain " + enemies.get(z).getGold() + " Gold dropped");
							player1.setExp(player1.getExp() + enemies.get(z).getExp());
							player1.setGold(player1.getGold() + enemies.get(z).getGold());
							enemies.remove(z);
							score++;
							z--;
						}
					}
					for (int x = 0; x < enemies.size(); x++) {
						enemies.get(x).doDamage(player1);
						if (player1.getHp() < 0) {
						player1.setHp(0);
						}
					}
					System.out.println(player1.getName() + " " + player1.stringHp());
					for (int y = 0; y < enemies.size(); y++) {
						System.out.println(enemies.get(y).getName() + " " + enemies.get(y).stringHp());
					}
				}
				}
				else {
					System.out.println("You died");
					System.out.println("Final score = " + score);
					System.out.println("you had " + player1.getGold() + " gold");
					alive = false;
					break;
					
				}
			}
		}
			else  if (doNext == 2) {
				player1.rest();
			}
			else if (doNext == 3) {				
				boolean inShop = true;
				int select = 0;
				while (select != 5) {
				System.out.println("What would you like to buy");
				System.out.println("You have " + player1.getGold() + " gold");
				System.out.println("1: Potion of endurance (50 gold)");
				System.out.println("2: Better armor (100 gold)");
				System.out.println("3: Potion of strength (50 gold)");
				System.out.println("4: Better weapon (100 gold)");
				System.out.println("5: Exit");
				select = input.nextInt();
				if (select == 1) {
					if (player1.getGold() >= 50) {
					player1.increaseEndurance(1);
					player1.setGold(player1.getGold() - 50);
					System.out.println("Purchase successful");
					}
					else {
					System.out.println("Not enough gold");
					}
				}
					else if (select == 2) {
						if (player1.getGold() >= 100) {
							player1.increaseArmor(1);
							player1.setGold(player1.getGold() - 100);
							System.out.println("Purchase successful");
					}
						else {
						System.out.println("Not enough gold");
						}
					}
					else if (select == 3) {
						if (player1.getGold() >= 50) {
						player1.increaseDamage(1);
						player1.setGold(player1.getGold() - 50);
						System.out.println("Purchase successful");
					}
						else {
							System.out.println("Not enough gold");
					}
					}
					else if (select == 4) {
						if (player1.getGold() >= 100) {
						player1.increaseAttackBonus(1);
						player1.setGold(player1.getGold() - 100);
						System.out.println("Purchase successful");
						}
						else {
							System.out.println("Not enough gold");
					}
					}
					else if (select == 5) {
						inShop = false;
					}
					else {
						System.out.println("Enter a valid selection");
				}
			}
			}
			else {
				System.out.println("Enter a valid selection");
			}
		}
	}
}
