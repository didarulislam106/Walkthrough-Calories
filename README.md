### Calorie Consumption Calculator

# Overview: The Calorie Consumption Calculator is an Android application developed using Jetpack Compose. It calculates daily calorie requirements based on the user's weight, gender, and activity level. The app follows the MVVM architecture and provides a user-friendly interface for inputting data and viewing estimated calorie needs.

## Features

# User Input Fields
1. Enter weight in kilograms.
2. Select gender (Male/Female).
3. Choose activity level from a dropdown menu.

#Calorie Calculation
1. Uses a formula based on gender and activity level.

# Real-Time Updates
1. Displays the estimated daily calorie intake dynamically.

## Technologies Used
1. Kotlin (Jetpack Compose for UI)
2. Android Studio (Development environment)

## Installation
1. Clone the repository: git clone https://github.com/didarulislam106/Walkthrough-Calories.git
2. Open the project in Android Studio.
3. Connect an emulator or a physical device.
4. Click Run to build and deploy the app.

## Usage
1. Launch the app.
2. Enter your weight in kilograms.
3. Select your gender.
4. Choose an activity level.
5. Click the Calculate button to see your estimated daily calorie requirement.

## Calorie Calculation Formula

The app uses the following formulas based on gender:
1. Male: Calories = Weight * 24

2. Female: Calories = Weight * 22

The result is then multiplied by an activity multiplier:
Activity Level

Multiplier

Light

1.3

Usual

1.5

Moderate

1.7

Hard

2.0

Very Hard

2.2
