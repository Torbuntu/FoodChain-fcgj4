# FoodChain-fcgj4

## Day 1
| Showcase |
|----|
| ![](Programs/FoodChain/Showcase/day1.gif?raw=true) |

Day 1 of the FC Game Jam 4. I selected a game title `Food Chain`. Set up some basic sprites (They'll need some lovin). 
Added a bit of game logic. So far so good. Need to continue implementing the matches and how to remove them from the board.

## Day 2
| Showcase |
|----|
| ![](Programs/FoodChain/Showcase/day2.gif?raw=true) |

Finished up some of the game mechanics (selections and super powers now work). Added a meter to display roughly how many pairs exist. Implemented a level to win (8). Added a "win" screen. Added an "instructions" screen. Game is now "playable".

## Day 3
| Showcase |
|----|
| ![](Programs/FoodChain/Showcase/day3.gif?raw=true) |

A fair bit of design overhaul went into today. The main grid is now a square for more play area. The meter for available pairs has been put inside of its own little jar and displays cyan unless in the danger zone (less than 14 pairs). 
Added lives so that when the board is empty of pairs the user loses a life and the board resets. This allows for longer play. Additional lives are earned with each level.
The meter at the top is now the `hunger` meter and will drain slowly over the duration of gameplay. Each level causes hunger to drain quicker, up to level 4. After level 4 the rate is constant.
The meter is filled with bonus combos of 3 to 6. 
Updated the instructions screen to be more useful and colorful. 
Added a warning symbol for when <4 pairs are available. 

## Day 4
| Showcase |
|----|
| ![](Programs/FoodChain/Showcase/day4.gif?raw=true) |

* Updated the Sprite sheet to look prettier. 
* Added a new Acheivments page with 10 acheivments.
* Added two new power ups. Bomb and Swap.
* Fixed a lot of the Controller controls.
* Updated the Game Over page.
* Added a page 2 to instructions.
* Made new notification for acheivment earnings.
* lots of code cleanup.

## Day 5

https://torbuntu.itch.io/foodchain-fcgj4

No gif to showcase today. Major changes were all audio changes. Added a line between swapping tiles.


## Day 6
| Showcase |
|----|
| ![](Programs/FoodChain/Showcase/day6.gif?raw=true) |

Tidying up final code and refactoring. Controller util is now able to be toggled via Ctrl C and you can get back by pressing Ctrl B.

Added a secret achievment. 

Fixed up some sprites and art styles.

Added screen shaking for lost lives and bomb explosions. 

Created starvation where you lose health for moving too slow.

more I forgot :( 
