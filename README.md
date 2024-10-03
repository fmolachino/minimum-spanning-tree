# Minimum Spanning Tree (MST) Project

## Project Description

This project implements a Minimum Spanning Tree (MST) algorithm using a modified version of Prim's algorithm. The goal is to calculate the minimum total connection cost between different geographical points (cities) while considering specific conditions such as distance, pricing, and provincial tax.

## Features

- Adds geographical points (cities) with coordinates (`x`, `y`) and province information.
- Calculates the minimum spanning tree connecting all points with the minimum cost.
- Customizable pricing model for connections:
  - Base price per kilometer.
  - Higher price for distances over 300 km.
  - Additional tax for connections between different provinces.
- Prevents adding the same city more than once.

## Requirements

- Java Development Kit (JDK) 8 or higher.

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/fmolachino/minimum-spanning-tree.git
   ```
   
2. Navigate to the project directory:

   ```bash
   cd minimum-spanning-tree
   ```








   
