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
