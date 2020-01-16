# Heroes-2.0

### Heroes is a web application where you can find victorious opponents. 

### Database Requirements
 The Database of the application needs to support:
#### User
* Has a Username - String
* Has a Password - String
* Has an Email - String
* Has a Hero - Entity
#### Hero
* Has a Username - String
* Has a Gender – (Male, Female)
* Has a Level - Integer
* Has a Stamina - Integer
* Has a Strength - Integer
* Has an Attack - Integer
* Has a Defence - Integer
* Has a List<HeroItems>(Inventory)
* Has User - Entity
  
#### Item
* Has a Name - String
* Has a Slot(Weapon, Helmet, Pauldron, Pads, Gauntlets)
* Has a Stamina - Integer
* Has a Strength - Integer
* Has an Attack - Integer
* Has a Defence - Integer
#### Hero Items
* Has a Hero - Entity
* Has an Item - Entity

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


### 1.Functional Requirements
The Functionality Requirements describe the functionality that the Application must support.
The application should provide Guest (not logged in) users with the functionality to:
* Login
* Register
* View the Index page.

The application should provide Users (logged in) with the functionality to:

* Logout
* Home
* Create Hero
* Create Item
* Profile
* Hero Details
* Merchant

User can have can only one Hero. When user has created a hero, he can access Merchant and Profile functionality.

On profile page you should see User – username and email, Hero – name, level and gender(appropriate picture).

After you create a item, it should appear in Merchant page. Each item needs to be shown in table like in the photo. When hero buys a item it will be added to his inventory. 
Hero can have only one weapon, helmet, pauldron, gauntlets or pads. After he buys a item, the item buy button should be hidden. 
When a hero buys a item his stats need to be updated with the item stats. 

When you fight a hero, the winner is this which damage is more. Damage is calculated by the next formula : heroAttack + heroStrength * 4 – opponentDefence + oponnentStamina * 2. The winner should level up.
Winners level should be increased by 1 and his strength and stamina should be increased with 5.
