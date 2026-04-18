# SC2002 Turn-Based Game

Ho Yan Jie (HOYA0013@E.NTU.EDU.SG),
Javier Lee Zheng Wei (JLEE394@E.NTU.EDU.SG),
Jaden Loh Jing Han (JLOH65@E.NTU.EDU.SG),
Jonnada Harini (HARINI1008@E.NTU.EDU.SG),
Dhruv Gupta (DHRUV022@E.NTU.EDU.SG)

## Project Highlights

- Role-based combat with distinct Warrior and Wizard playstyles
- Speed-based turn order that changes the flow of each round
- Consumable item system
- Multiple difficulty levels with enemy reinforcements on harder modes
- Status effects and cooldown management
- Additional polish features such as health bars, loot recovery, and end-of-game summaries

## Project Overview

In this game, the player selects a role, chooses two starting items, and enters a battle against enemy waves determined by the selected difficulty. The game is then played in rounds, with turn order based on combatant speed. On each turn, the player can attack, defend, use an item, or trigger a role-specific special skill. The game ends when either the player is defeated or all enemies, including reinforcements, are eliminated.

This project was designed to demonstrate essential software engineering concepts such as abstraction, inheritance, polymorphism, SOLID principles, while still delivering a user-friendly gameplay experience in a console environment.

### Playable Roles

| Role | Base Stats | Special Skill |
| --- | --- | --- |
| Warrior | HP 260, ATK 40, DEF 20, SPD 30 | Shield Bash: deals damage and stuns one enemy |
| Wizard | HP 200, ATK 50, DEF 10, SPD 20 | Arcane Blast: damages all enemies and gains +10 ATK for each kill |

### Item System

| Item | Function |
| --- | --- |
| Potion | Restores 100 HP |
| Power Stone | Activates the player's special skill without consuming cooldown |
| Smoke Bomb | Prevents enemy attacks from dealing damage for 2 turns |

### Difficulty Settings

| Difficulty | Initial Enemies | Reinforcements |
| --- | --- | --- |
| Easy | 3 Goblins | None |
| Medium | 1 Goblin, 1 Wolf | 2 Wolves |
| Hard | 2 Goblins | 2 Wolves, 1 Goblin |

### Core Battle Mechanics

- Speed-based turn order using a dedicated turn-order strategy
- Four player actions: Basic Attack, Defend, Use Item, and Special Skill
- Status effects including Stun, Defend, Smoke Bomb, and Arcane Blast buff stacks
- Special skill cooldown tracking across rounds
- Automatic enemy cleanup, backup spawn checks, and win/loss evaluation
- Replay flow that allows the player to restart with the same settings or start a new run

## User Friendliness And Innovation

The game was intentionally built to be more than a plain text combat loop. Several interface and flow decisions were made to improve usability and presentation:

- Guided setup flow for selecting role, items, and difficulty
- Input validation for menu selections to prevent invalid game states
- Clear battle panels for enemy list, player status, and turn order
- Active status effect and cooldown displays during combat
- Structured round summaries for better tracking of battle progression
- Replay options that reduce setup repetition after a completed run

These improvements make the game easier to understand during actual play and creates a smoother experience for players who are unfamiliar with the system.

## Additional Features Implemented

### 1. Player Healthbar UI

The project includes a text-based health bar system that appears in battle displays, enemy listings, player status panels, and round summaries. This makes HP changes immediately visible and improves readability compared to plain numbers alone.

### 2. Random Enemy Drops

When an enemy is defeated, there is a 25% chance for a loot drop event to trigger bonus HP recovery for the player. This adds unpredictability to combat and creates a small reward loop that can help the player recover during longer fights.

### 3. End Of Game Summary

At the end of each battle, the game displays a final result screen with victory or defeat statistics and prints the accumulated battle log. This gives players a full summary of the encounter instead of ending the session abruptly.

## Design And Development Approach

The codebase is structured around reusable abstractions so that future extensions can be added without rewriting the game loop.

- `BattleEngine` handles combat flow, round progression, reinforcement spawning, and win-condition checks
- `UserInterface` is responsible for input, display formatting, prompts, and post-battle summaries
- Abstract types such as `Combatant`, `PlayerRole`, `Item`, and `Difficulty` support clean specialization
- Interfaces such as `IAction`, `IStatusEffect`, `IEnemyStrategy`, and `ITurnOrderStrategy` enable polymorphic behavior

This makes the system easier to maintain and supports future additions such as new roles, enemy types, items, or AI behaviors.
