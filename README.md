# <h1> This is a project for my object oriented programing class
This game spawns a character that you can customize with different stats. After you have a character set up you can begin your adventure. In the adventure you can spawn different enemies to fight. As you fight you will receive gold to spend in the shop.
## <h2> Motivation
What motivated me to make this game was that i have always had a passion for games and wanted to make one that i had control over. To be able to make a game exatly the way i want it.
## <h2> Program being run
![Game being run](https://github.com/dallinsavage/RPG-Game/blob/With_GUI/Screen%20Shot%202020-10-21%20at%204.41.39%20PM.png)
## <h2> Code example
  ```
  		public void doDamage(Character target) {
		int attack = (int)((Math.random() * 10) + (Math.random() * 10)) ;
		int targetHp = target.getHp();
		int targetArmor = target.getArmor();
		if (attack + attackBonus > targetArmor) {
			target.setHp(targetHp - damage);
		}
	}
  ```
