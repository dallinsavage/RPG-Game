import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Display_Game extends Application {
	private int currentEnemy = 0;
	
	public int getCurrentEnemy() {
		return currentEnemy;
	}
	public void setCurrentEnemy(int newEnemy) {
		currentEnemy = newEnemy;
	}
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Player player1 = new Player();
		player1.setX(100);
		player1.setY(250);
		GridPane spawnPlayerPane = new GridPane();
		GridPane spawnEnemiesPane = new GridPane();
		BorderPane campPane = new BorderPane();
		Pane combatPane = new Pane();
		GridPane shopPane = new GridPane();
		Scene spawnEnemies = new Scene(spawnEnemiesPane, 750, 500);
		Scene spawnPlayer = new Scene(spawnPlayerPane, 750, 500);
		Scene camp = new Scene(campPane, 750, 500);
		Scene combat = new Scene(combatPane, 750, 500);
		Scene shop = new Scene(shopPane, 750, 500);
		
		
		//Spawn Player

		Label pointsLeft = new Label("Points left");
		TextField points = new TextField("2");
		points.setEditable(false);
		Label armor = new Label("Armor");
		Label damage = new Label("Damage");
		Label attackBonus = new Label("Attack Bonus");
		Label endurance = new Label("Endurance");
		Button plusArmor = new Button("+");
		Button plusDamage = new Button("+");
		Button plusAttackBonus = new Button("+");
		Button plusEndurance = new Button("+");
		spawnPlayerPane.setAlignment(Pos.CENTER);
		spawnPlayerPane.addColumn(0, pointsLeft, armor, damage, attackBonus, endurance);
		spawnPlayerPane.addColumn(1, points, plusArmor, plusDamage, plusAttackBonus, plusEndurance);
		
		
		//Spawn Player events
		
		plusArmor.setOnAction(e -> {
			if (!points.getText().equals("1")) {
			player1.increaseArmor(1);
			points.setText(countDown(points.getText()));
		}
			else {
				primaryStage.setScene(camp);
			}
		});
		plusDamage.setOnAction(e -> {
			if (!points.getText().equals("1")) {
			player1.increaseDamage(1);
			points.setText(countDown(points.getText()));
			}
			else {
				primaryStage.setScene(camp);
			}
		});
		plusAttackBonus.setOnAction(e -> {
			if (!points.getText().equals("1")) {
			player1.increaseAttackBonus(1);
			points.setText(countDown(points.getText()));
			}
			else {
				primaryStage.setScene(camp);
			}
		});
		plusEndurance.setOnAction(e -> {
			if (!points.getText().equals("1")) {
			player1.increaseEndurance(1);
			points.setText(countDown(points.getText()));
			}
			else {
				primaryStage.setScene(camp);
			}
		});

		//Spawn Enemies
		
		Button spawn = new Button("Spawn");
		Label skeleton = new Label("Skeleton");
		TextField skeletonNum = new TextField("0");
		Label zombie = new Label("Zombie");
		TextField zombieNum = new TextField("0");
		Label tank = new Label("Tank");
		TextField tankNum = new TextField("0");
		Label boss = new Label("Boss");
		TextField bossNum = new TextField("0");
		spawnEnemiesPane.setAlignment(Pos.CENTER);
		spawnEnemiesPane.addColumn(0, skeleton, zombie, tank, boss, spawn);
		spawnEnemiesPane.addColumn(1, skeletonNum, zombieNum, tankNum, bossNum);
		
		//Spawn Enemies events
		
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		spawn.setOnAction(e -> {
		primaryStage.setScene(combat);
		int numOfSkeletons =	Integer.parseInt(skeletonNum.getText());
		int numOfZombies =	Integer.parseInt(zombieNum.getText());
		int numOfTanks =	Integer.parseInt(tankNum.getText());
		int numOfBoss =	Integer.parseInt(bossNum.getText());
		for (int s = 0; s < numOfSkeletons; s++) {
			enemies.add(new Skeleton());
		}
		for (int z = 0; z < numOfZombies; z++) {
			enemies.add(new Zombie());
		}
		for (int t = 0; t < numOfTanks; t++) {
			enemies.add(new Tank());
		}
		for (int b = 0; b < numOfBoss; b++) {
			enemies.add(new Boss());
		}
		
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.size() > 1) {
				enemies.get(i).setX(500);
				enemies.get(i).setY(400 / enemies.size() + i * 50);
			combatPane.getChildren().add(enemies.get(i).draw(enemies.get(i).getX(), enemies.get(i).getY()));
		}
			else {
				enemies.get(i).setX(500);
				enemies.get(i).setY(250);
				combatPane.getChildren().add(enemies.get(i).draw(500, 250));
			}
		}
		});

		
		//Camp
		
		Button btRest = new Button("Rest");
		Button btShop = new Button("Shop");
		Button btCombat = new Button("Continue Adventure");
		HBox campHBox = new HBox(200);
		campHBox.setAlignment(Pos.CENTER);
		campHBox.getChildren().addAll(btRest, btShop, btCombat);
		campPane.setCenter(campHBox);
		
		//Camp events
		
		btRest.setOnAction(e -> {
			player1.rest();
		});
		btShop.setOnAction(e -> {
			primaryStage.setScene(shop);
			primaryStage.setTitle("Shop");
		});
		btCombat.setOnAction(e -> {
			primaryStage.setScene(spawnEnemies);
			primaryStage.setTitle("Adventure");
		});
		
		
		
		//Shop

		Button btEndurance = new Button("Potion of Endurance (50g)");
		Button btArmor = new Button("Better Armor (100g)");
		Button btStrength = new Button("Potion of strength (50g)");
		Button btAttack = new Button("Better Weapon (100g)");
		Button back = new Button("Back");
		shopPane.addColumn(0, btEndurance, btArmor);
		shopPane.addColumn(1, btStrength, btAttack, back);

		shopPane.setAlignment(Pos.CENTER);
		shopPane.setHgap(50);
		shopPane.setVgap(50);
		
		//Shop events
		
		btEndurance.setOnAction(e -> {
			if (player1.getGold() >= 50) {
				player1.increaseEndurance(1);
			}
		});
		btArmor.setOnAction(e -> {
			if (player1.getGold() >= 100) {
				player1.increaseArmor(1);
			}
		});
		btStrength.setOnAction(e -> {
			if (player1.getGold() >= 50) {
				player1.increaseDamage(1);
			}
		});
		btAttack.setOnAction(e -> {
			if (player1.getGold() >= 100) {
				player1.increaseAttackBonus(1);
			}
		});
		back.setOnAction(e -> {
			primaryStage.setScene(camp);
			primaryStage.setTitle("Camp");
		});
	
		
		
		//Combat
		combatPane.getChildren().add(player1.draw(player1.getX(), player1.getY()));
		TextField targetSelect = new TextField("0"); 
		Label target = new Label("Target");
		Button btTarget = new Button("Attack");
		HBox targeting = new HBox(20);
		targeting.getChildren().addAll(target, targetSelect, btTarget);
		combatPane.getChildren().add(targeting);
		targeting.setAlignment(Pos.BOTTOM_CENTER);
		
		
		//Combat events
		
		
		btTarget.setOnAction(e -> {
			runCombat(enemies, player1, Integer.parseInt(targetSelect.getText()), combatPane);
		});
		
		primaryStage.setTitle("Spawn");
		primaryStage.setScene(spawnPlayer);
		primaryStage.show();
		primaryStage.setResizable(false);		
	}
	public void runCombat(ArrayList<Enemy> enemies, Player player1, int target, Pane pane) {
		EventHandler<ActionEvent> movePlayerRight = e -> {
			player1.moveRight();
			drawPane(player1, enemies, pane);
		};
		EventHandler<ActionEvent> movePlayerLeft = e -> {
			player1.moveLeft();
			drawPane(player1, enemies, pane);
		};
		EventHandler<ActionEvent> moveEnemyRight = e -> {
			for (int x = 0; x < enemies.size(); x++) {
				enemies.get(x).moveRight();
			}
			drawPane(player1, enemies, pane);
			System.out.println("move right");
			System.out.println(getCurrentEnemy());
		};
		EventHandler<ActionEvent> moveEnemyLeft = e -> {
			for (int x = 0; x < enemies.size(); x++) {
				enemies.get(x).doDamage(player1);
				enemies.get(x).moveLeft();
				if (player1.getHp() < 0) {
				player1.setHp(0);
				}
		}
			drawPane(player1, enemies, pane);
			System.out.println("move left");
		};
		player1.doDamage(enemies.get(target));
			Timeline Attack = new Timeline(new KeyFrame(Duration.millis(50), movePlayerRight), new KeyFrame(Duration.millis(300), movePlayerLeft), new KeyFrame(Duration.millis(50), moveEnemyLeft), new KeyFrame(Duration.millis(300), moveEnemyRight));
			Attack.play();
			for (int z = 0; z < enemies.size(); z++) {
				if (enemies.get(z).getHp() <= 0) {
					player1.setExp(player1.getExp() + enemies.get(z).getExp());
					player1.setGold(player1.getGold() + enemies.get(z).getGold());
					enemies.remove(z);
					z--;
				}
			}
		}
	public String countDown(String currentNum) {
		int intValue = Integer.parseInt(currentNum);
		intValue = intValue - 1;
		String newNum = String.valueOf(intValue);
		return newNum;
	}
	public String countUp(String currentNum) {
		int intValue = Integer.parseInt(currentNum);
		intValue = intValue + 1;
		String newNum = String.valueOf(intValue);
		return newNum;
	}
	public void drawPane(Player player1, ArrayList<Enemy> enemies, Pane pane) {
		pane.getChildren().clear();
		pane.getChildren().add(player1.draw(player1.getX(), player1.getY()));
		for (int i = 0; i < enemies.size(); i++) {
			pane.getChildren().add(enemies.get(i).draw(enemies.get(i).getX(), enemies.get(i).getY()));
		}
		TextField targetSelect = new TextField("0"); 
		Label target = new Label("Target");
		Button btTarget = new Button("Attack");
		HBox targeting = new HBox(20);
		targeting.getChildren().addAll(target, targetSelect, btTarget);
		pane.getChildren().add(targeting);
		targeting.setAlignment(Pos.BOTTOM_CENTER);
	}
}
