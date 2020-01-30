# Heroes-2.0

## Technology stack used to implement the project are: 

* Spring Boot
* Spring Security
* Thymeleaf
* Hibernate
* JPA
* HTML5
* CSS3
* Bootstrap4
* JUnit
* Cloudinary


### Heroes is a web application where you can find victorious opponents. 

### Database
 The Database of the application supports:
#### User
* Username - String
* Password - String
* Email - String
* Hero - Entity
#### Hero
* Username - String
* Gender – (Male, Female)
* Level - Integer
* Stamina - Integer
* Strength - Integer
* Attack - Integer
* Defence - Integer
* List<HeroItems>(Inventory)
* User - Entity
  
#### Item
* Name - String
* Slot(Weapon, Helmet, Pauldron, Pads, Gauntlets)
* Stamina - Integer
* Strength - Integer
* Attack - Integer
* Defence - Integer
#### Hero Items
* Hero - Entity
* Item - Entity

### 1.Templates
Index Template (route = “/”) (logged out user)

![arena](https://res.cloudinary.com/edzhevit/image/upload/v1579169808/Picture1_kfegc2.png)

Login Template (route = “/users/login”) (logged out user)

![arena](https://res.cloudinary.com/edzhevit/image/upload/v1579170486/Picture2_ymbbfy.png)

Register Template (route = “/users/register”) (logged out user)

![arena](https://res.cloudinary.com/edzhevit/image/upload/v1579170626/Picture3_j0pjrr.png)

Home Template (route=”/home”) (logged in user)

![arena](https://res.cloudinary.com/edzhevit/image/upload/v1579170662/Picture4_fwqabp.png)

Hero Create Template (route=”/heroes/create”) (logged in user)

![arena](https://res.cloudinary.com/edzhevit/image/upload/v1579170691/Picture5_hb30wb.png)

Home Template (route=”/home”) (logged in user) 
Home after hero is created.

![arena](https://res.cloudinary.com/edzhevit/image/upload/v1579170739/Picture6_vh6af3.png)

Profile Template (route=”/users/profile”) (logged in user) 

![arena](https://res.cloudinary.com/edzhevit/image/upload/v1579170778/Picture7_yyrs2s.png)

Hero Details Template (route=”/heroes/details/(name)”) (logged in user) 
Hero details when we don't have items

![arena](https://res.cloudinary.com/edzhevit/image/upload/v1579170866/Picture8_zz3veh.png)

Create Item (route=”/items/create”) (logged in user) 

![arena](https://res.cloudinary.com/edzhevit/image/upload/v1579170907/Picture9_vc7zhz.png)

Merchant (route=”/items/merchant”) (logged in user) 

![arena](https://res.cloudinary.com/edzhevit/image/upload/v1579170930/Picture10_wvkym6.png)

Hero Details (route=”/heroes/details/(name)”) (logged in user) 
Hero details after we buy items.

![arena](https://res.cloudinary.com/edzhevit/image/upload/v1579170968/Picture11_r5vsgw.png)

Home (route=”/home”) (logged in user) 
When you have created a hero, and there are other heroes, you should list all heroes you can fight.

![arena](https://res.cloudinary.com/edzhevit/image/upload/v1579170999/Picture12_qsclji.png)

Fight (route=”/heroes/fight/(heroName)”) (logged in user) 
On left you should show your hero and on right your opponent. In the middle show the winner.

![arena](https://res.cloudinary.com/edzhevit/image/upload/v1579171030/Picture13_r2sn9a.png)


### 1.Functionality

The application provides Guest (not logged in) users with the functionality to:
* Login
* Register
* View the Index page.

The application provides Users (logged in) with the functionality to:

* Logout
* Home
* Create Hero
* Create Item
* Profile
* Hero Details
* Merchant

User can have can only one Hero. When user creates a hero, he can access Merchant and Profile functionality.

On profile page you should see User – username and email, Hero – name, level and gender(appropriate picture).

After you create a item, it should appear in Merchant page. Each item needs to be shown in table like in the photo. When hero buys a item it will be added to his inventory. 
Hero can have only one weapon, helmet, pauldron, gauntlets or pads. After he buys a item, the item buy button should be hidden. 
When a hero buys an item his stats are updated with the item stats. 

When you fight a hero, the winner is this which damage is more. Damage is calculated by the next formula : heroAttack + heroStrength * 4 – opponentDefence + oponnentStamina * 2. The winner levels up.
Winners level is increases by 1 and his strength and stamina increases with 5.
