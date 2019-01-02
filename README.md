
##SetUp Instructions ##
 In order to successfully set up our project, the user should first git clone into a certain directory,
 And then, open android studio, choose "open another project", select the folder, click into the group file,
 and proceed to GameCenter folder, then open it. Run build on android studio, wait for a few minutes before the
 whole project is synced and built successfully. Then you can run this program via an emulator.

 To set up and run unit test related to mock, please follow instruction in
 https://developer.android.com/training/testing/unit-testing/local-unit-tests


## Prerequisites ##
 This game is only designed to be run on Pixel 2 with API 27, as per the project setup instructions.
 Although, it is also possible for the game to be run on other specifications (such as a lower API
 version or a different-sized phone, please keep in mind there might be differences to the overall
 appearance and design due to incompatibility.)
 
## Description ##
To enter this game, the user has to 
 successfully register (or login to an existing account), which will then be directed to the game 
 center. There, the user can choose which games they would like to play by clicking/pressing onto
 the corresponding images (i.e. press the tiles image to enter "Sliding Tiles" game).In the game 
 center, the users can also access their personal profiles, which will display recent games played

 ## Tips ##
 * You could use the android built-in return/back button (usually located at the bottom for most android
 phones) to return to the previous page (or "activity").  

 * The profile page will show the number of steps you got in your latest game. (whether it's
 complete or not). You can also upload your own photo by clicking on the profile button now.

 * Start a new game after completed one, or else (if you continue load saved game) your number of
 steps will increase, which is not wanted in this game.

 * Scores (on scoreboard) are calculated and ranked by the LEAST number of moves taken to win the
 give game.

## Build With ##
 * Android Studio
 * Gradle

## Games Achieved: ##
 * sliding puzzle
 * 2048 game
 * sudoku game

## Winning Condition Of Games ##
* "sliding puzzle" will follow the traditional rule of tile games
* "2048" you'll win if you get a number of 2048, then you win
* "saduko" same as the traditional one 

## License ##
This project is licensed under the MIT License

## Author ##
This game is implemented by a group of 4 people, junxuan, timothy yilin Luo and quanzhou Li