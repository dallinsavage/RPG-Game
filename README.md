# <h1> This is a project for my object oriented programing class
This game spawns a character that you can customize with different stats. After you have a character set up you can begin your adventure. In the adventure you can spawn different enemies to fight. As you fight you will receive gold to spend in the shop.
## <h2> Motivation
What motivated me to make this game was that i have always had a passion for games and wanted to make one that i had control over. To be able to make a game exatly the way i want it.
## <h2> Program being run
![Game being run camp](https://github.com/dallinsavage/RPG-Game/blob/master/Screen%20Shot%202021-02-01%20at%205.12.19%20PM.png)
![Game being run combat](https://github.com/dallinsavage/RPG-Game/blob/master/Screen%20Shot%202021-02-02%20at%203.56.22%20PM.png)
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
