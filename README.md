# freeScore
*This project is still under development.*

The main motivation of this project is to provide a free score server for game developers. As game developer you might used Google or Apple APIs to store the scores of your players, the main problem is that your players need to have an account in this third system in order to have your score system running.

This project is 100% free and it will provide you a web application when you, as game developer will be able to manage the system and a public REST API which your games can use to send the scores, retrieve leader boards, etc. 

## Administration website
The administration website could be used by two different roles, admins and game developers.

This administration website is a tool to allow you an easy way to admin your score server, but for sure, it's not mandatory to have it running, it's completely fine if you fill the database manually or using the public API.

The possible operations for each role are:

* Admin
    * Users management
        * View a list of users
        * Activate / Deactivate an user
        * Change role Admin - Game Developer
* Game developer
    * Sign up
    * Sign in
    * Games management
        * View a list of all he/she’s games
        * Create / delete / update a game
        * View all the score of all players for a game
* Both
    * Change user password

## Public API
To be defined.