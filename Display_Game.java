import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Display_Game extends Application {
	public static void main(String[] args) {
		launch(args);

	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		Label gold = new Label();
		Player player1 = new Player();
		player1.setX(100);
		player1.setY(250);
		StackPane endPane = new StackPane();
		GridPane spawnPlayerPane = new GridPane();
		GridPane spawnEnemiesPane = new GridPane();
		BorderPane campPane = new BorderPane();
		Pane combatPane = new Pane();
		GridPane shopPane = new GridPane();
		shopPane.setStyle("-fx-background-color:rgb(135,206,235); -fx-opacity:1;");
		spawnPlayerPane.setStyle("-fx-background-color:rgb(135,206,235); -fx-opacity:1;");
		spawnEnemiesPane.setStyle("-fx-background-color:rgb(135,206,235); -fx-opacity:1;");
		Scene spawnEnemies = new Scene(spawnEnemiesPane, 750, 500);
		Scene spawnPlayer = new Scene(spawnPlayerPane, 750, 500);
		Scene camp = new Scene(campPane, 750, 500);
		Scene combat = new Scene(combatPane, 750, 500);
		Scene shop = new Scene(shopPane, 750, 500);
		Scene end = new Scene(endPane,750, 500);
		
		// end pane
		
		Label die = new Label("You died");
		die.setTextFill(Color.DARKRED);
		Rectangle background = new Rectangle(0,0, 750, 500);
		die.setScaleX(10);
		die.setScaleY(10);
		endPane.getChildren().addAll(background, die);

		
		//Spawn Player

		Label pointsLeft = new Label("Points left");
		TextField points = new TextField("20");
		points.setEditable(false);
		Label armor = new Label("Armor");
		Label damage = new Label("Damage");
		Label attackBonus = new Label("Attack Bonus");
		Label endurance = new Label("Endurance");
		TextField armorNum = new TextField(String.valueOf(player1.getArmor()));
		armorNum.setEditable(false);
		TextField damageNum = new TextField(String.valueOf(player1.getDamage()));
		damageNum.setEditable(false);
		TextField attackBonusNum = new TextField(String.valueOf(player1.getAttackBonus()));
		attackBonusNum.setEditable(false);
		TextField enduranceNum = new TextField(String.valueOf(player1.getEndurance()));
		enduranceNum.setEditable(false);
		Button plusArmor = new Button("+");
		Button plusDamage = new Button("+");
		Button plusAttackBonus = new Button("+");
		Button plusEndurance = new Button("+");
		spawnPlayerPane.setAlignment(Pos.CENTER);
		spawnPlayerPane.addColumn(0, pointsLeft, armor, damage, attackBonus, endurance);
		spawnPlayerPane.addColumn(1, points, plusArmor, plusDamage, plusAttackBonus, plusEndurance);
		spawnPlayerPane.addRow(1, armorNum);
		spawnPlayerPane.addColumn(2, damageNum, attackBonusNum, enduranceNum);
		
		
		//Spawn Player events
		
		plusArmor.setOnAction(e -> {
			if (!points.getText().equals("1")) {
			player1.increaseArmor(1);
			points.setText(countDown(points.getText()));
			armorNum.setText(countUp(armorNum.getText()));
		}
			else {
				primaryStage.setScene(camp);
			}
		});
		plusDamage.setOnAction(e -> {
			if (!points.getText().equals("1")) {
			player1.increaseDamage(1);
			points.setText(countDown(points.getText()));
			damageNum.setText(countUp(damageNum.getText()));
			}
			else {
				primaryStage.setScene(camp);
			}
		});
		plusAttackBonus.setOnAction(e -> {
			if (!points.getText().equals("1")) {
			player1.increaseAttackBonus(1);
			points.setText(countDown(points.getText()));
			attackBonusNum.setText(countUp(attackBonusNum.getText()));
			}
			else {
				primaryStage.setScene(camp);
			}
		});
		plusEndurance.setOnAction(e -> {
			if (!points.getText().equals("1")) {
			player1.increaseEndurance(1);
			player1.setHp(player1.getMaxHp());
			points.setText(countDown(points.getText()));
			enduranceNum.setText(countUp(enduranceNum.getText()));
			}
			else {
				primaryStage.setScene(camp);
			}
		});
		combatPane.getChildren().add(player1.draw(player1.getX(), player1.getY()));

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
		Rectangle health = new Rectangle(player1.getX(), player1.getY() - 50, player1.getHp(), 10);
		health.setFill(Color.RED);
		combatPane.getChildren().add(health);
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
			Rectangle enemyHealth = new Rectangle(enemies.get(i).getX() - 20,enemies.get(i).getY() - 20, enemies.get(i).getHp(), 10);
			enemyHealth.setFill(Color.RED);
			combatPane.getChildren().add(enemyHealth);
			Label label = new Label(String.valueOf(i));
			label.setLayoutX(enemies.get(i).getX());
			label.setLayoutY(enemies.get(i).getY());
			combatPane.getChildren().add(label);
			
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
		HBox campHBox = new HBox(230);
		Rectangle rec1 = new Rectangle(350, 320, 25, 75);
		Rectangle rec2 = new Rectangle(330, 300, 25, 100);
		Rectangle rec3 = new Rectangle(370, 300, 25, 100);
		rec1.setFill(Color.BROWN);
		rec2.setFill(Color.BROWN);
		rec3.setFill(Color.BROWN);
		rec2.setRotate(45);
		rec3.setRotate(-45);
		Rectangle sky = new Rectangle(0, 0, 750, 300);
		sky.setFill(Color.SKYBLUE);
		Rectangle ground = new Rectangle(0, 300, 750, 200);
		ground.setFill(Color.DARKGREEN);
		Rectangle path = new Rectangle(680, 280, 75, 250);
		Circle pathCircle = new Circle(642, 480, 40);
		pathCircle.setFill(Color.TAN);
		path.setRotate(45);
		path.setFill(Color.TAN);
		Polygon tent = new Polygon(0, 400, 100, 50, 200, 400);
		Polygon tent1 = new Polygon(100, 400, 100, 70, 140, 400);
		Polygon fire1 = new Polygon(330, 350, 300, 270, 340, 320, 360, 270, 380, 320, 420, 270, 400, 350);
		Polygon fire2 = new Polygon(330, 350, 305, 280, 340, 330, 360, 280, 380, 330, 415, 280, 400, 350);
		fire2.setFill(Color.YELLOW);
		fire1.setFill(Color.ORANGE);
		tent.setFill(Color.DARKOLIVEGREEN);
		campHBox.setAlignment(Pos.CENTER);
		campHBox.getChildren().addAll(btRest, btShop, btCombat);
		campPane.getChildren().addAll(sky, ground, rec1, rec2, rec3, fire1, fire2, tent, tent1, path, pathCircle);
		campPane.setCenter(campHBox);
		
		//Camp events
		
		btRest.setOnAction(e -> {
			player1.rest();
			message(campPane, "You Feel rested, HP restored");
		});
		btShop.setOnAction(e -> {
			primaryStage.setScene(shop);
			primaryStage.setTitle("Shop");
			gold.setText("Player Gold: " + String.valueOf(player1.getGold()));
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
		shopPane.addColumn(0, btEndurance, btArmor, gold);
		shopPane.addColumn(1, btStrength, btAttack, back);
		shopPane.setAlignment(Pos.CENTER);
		shopPane.setHgap(50);
		shopPane.setVgap(50);
		
		//Shop events
		
		btEndurance.setOnAction(e -> {
			
			if (player1.getGold() >= 50) {
				player1.setGold(player1.getGold() - 50);
				player1.increaseEndurance(1);
				messageGrid(shopPane, "Purchase successful");
				gold.setText("Player Gold: " + String.valueOf(player1.getGold()));
			}
			else {
				messageGrid(shopPane, "Not enough gold");
			}
		});
		btArmor.setOnAction(e -> {
			
			if (player1.getGold() >= 100) {
				player1.setGold(player1.getGold() - 100);
				player1.increaseArmor(1);
				messageGrid(shopPane, "Purchase successful");
				gold.setText("Player Gold: " + String.valueOf(player1.getGold()));
			}
			else {
				messageGrid(shopPane, "Not enough gold");
			}
		});
		btStrength.setOnAction(e -> {

			if (player1.getGold() >= 50) {
				player1.setGold(player1.getGold() - 50);
				player1.increaseDamage(1);
				messageGrid(shopPane, "Purchase successful");
				gold.setText("Player Gold: " + String.valueOf(player1.getGold()));
			}
			else {
				messageGrid(shopPane, "Not enough gold");
			}
		});
		btAttack.setOnAction(e -> {

			if (player1.getGold() >= 100) {
				player1.setGold(player1.getGold() - 100);
				player1.increaseAttackBonus(1);
				messageGrid(shopPane, "Purchase successful");
				gold.setText("Player Gold: " + String.valueOf(player1.getGold()));
			}
			else {
				messageGrid(shopPane, "Not enough gold");
			}
		});
		back.setOnAction(e -> {
			primaryStage.setScene(camp);
			primaryStage.setTitle("Camp");
		});
	
		
		
		//Combat
		
		Rectangle floor = new Rectangle(0, 0, 750, 500);
		floor.setFill(Color.DARKGREEN);
		combatPane.getChildren().add(floor);
		combatPane.getChildren().add(player1.draw(player1.getX(), player1.getY()));
		Rectangle health = new Rectangle(player1.getX(), player1.getY() - 50, player1.getHp(), 10);
		health.setFill(Color.RED);
		combatPane.getChildren().add(health);
		TextField targetSelect = new TextField("0"); 
		Label target = new Label("Target");
		Button btTarget = new Button("Attack");
		HBox targeting = new HBox(20);
		targeting.getChildren().addAll(target, targetSelect, btTarget);
		combatPane.getChildren().add(targeting);
		targeting.setAlignment(Pos.BOTTOM_CENTER);
		
		
		//Combat events
		
		
		btTarget.setOnAction(e -> {
			runCombat(enemies, player1, Integer.parseInt(targetSelect.getText()), combatPane, targeting, end, camp, primaryStage);
		});
		
		primaryStage.setTitle("Spawn");
		primaryStage.setScene(spawnPlayer);
		primaryStage.show();
		primaryStage.setResizable(false);		
	}
	public void runCombat(ArrayList<Enemy> enemies, Player player1, int target, Pane combatPane, Pane targeting, Scene end, Scene camp, Stage primaryStage) {
		player1.doDamage(enemies.get(target));
		combatPane.getChildren().clear();
		Rectangle floor = new Rectangle(0, 0, 750, 500);
		floor.setFill(Color.DARKGREEN);
		combatPane.getChildren().addAll(floor, targeting);
		Rectangle health = new Rectangle(player1.getX(), player1.getY() - 50, player1.getHp(), 10);
		health.setFill(Color.RED);
		combatPane.getChildren().add(health);
		PathTransition pt = player1.animate(combatPane);
		combatPane.getChildren().remove(player1);
		pt.play();
			for (int z = 0; z < enemies.size(); z++) {
				if (enemies.get(z).getHp() <= 0) {
					player1.setExp(player1.getExp() + enemies.get(z).getExp());
					player1.setGold(player1.getGold() + enemies.get(z).getEnemyGold());
					message(combatPane, enemies.get(z).getName() + " was slain " + enemies.get(z).getEnemyGold() + " gold recieved");
					enemies.remove(z);
					z--;
				}
				
				else {
					pt = enemies.get(z).animate(combatPane);
					pt.play();
					enemies.get(z).doDamage(player1);
					if (player1.getHp() <= 0) {
						player1.setHp(0);
						primaryStage.setScene(end);
						}
				}
				for (int x = 0; x < enemies.size(); x++) {
					Rectangle enemyHealth = new Rectangle(enemies.get(x).getX() - 20,enemies.get(x).getY() - 20, enemies.get(x).getHp(), 10);
					enemyHealth.setFill(Color.RED);
					combatPane.getChildren().add(enemyHealth);
					Label label = new Label(String.valueOf(x));
					label.setLayoutX(enemies.get(x).getX());
					label.setLayoutY(enemies.get(x).getY());
					combatPane.getChildren().add(label);
				}
			}
			if (enemies.isEmpty()) {
				primaryStage.setScene(camp);
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
	public void message(Pane pane, String message) {
		Label label = new Label();
		Pane labelPane = new Pane(label);
		label.setTextFill(Color.BLUE);
		pane.getChildren().add(labelPane);
		labelPane.setLayoutY(480);
		labelPane.setLayoutX(10);
		label.setText(message);
		FadeTransition ft = new FadeTransition(Duration.seconds(1.0), label);
		ft.setFromValue(1.0);
		ft.setToValue(0);
		ft.play();
		ft.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				pane.getChildren().remove(labelPane);
			}
		});
	}
	public void messageGrid(GridPane pane, String message) {
		Label label = new Label();
		Pane labelPane = new Pane(label);
		label.setTextFill(Color.BLUE);
		pane.add(labelPane, 0, 3);
		label.setText(message);
		FadeTransition ft = new FadeTransition(Duration.seconds(1.0), label);
		ft.setFromValue(1.0);
		ft.setToValue(0);
		ft.play();
		ft.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				pane.getChildren().remove(labelPane);
			}
		});
	}
}
