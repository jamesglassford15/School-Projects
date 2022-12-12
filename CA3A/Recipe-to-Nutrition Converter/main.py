# CS 3A  - Lab 3 Framework
# Decided to make my chipotle order since I work there -
# all the nutrition facts are pulled straight from their website.
# nutrition facts are based off of servings of 4oz, so I altered
# the way the program calculates the results based on this fact.
import sys

# food #1 intended constants
FOOD_1_NAME = "white rice"
FOOD_1_CALORIES_P4OZ = 210  # in calories
FOOD_1_CARBS_P4OZ = 40  # in grams
FOOD_1_PROTEIN_P4OZ = 4 # in grams
FOOD_1_TOTAL_FAT_P4OZ = 0.5 # in grams
FOOD_1_SODIUM_P4OZ = 350 # in milligrams

# food #2 intended constants
FOOD_2_NAME = "chicken"
FOOD_2_CALORIES_P4OZ = 210  # in calories
FOOD_2_CARBS_P4OZ = 0  # in grams
FOOD_2_PROTEIN_P4OZ = 32 # in grams
FOOD_2_TOTAL_FAT_P4OZ = 7 # in grams
FOOD_2_SODIUM_P4OZ = 310 # in milligrams


# food #3 intended constants
FOOD_3_NAME = "fresh tomato salsa"
FOOD_3_CALORIES_P4OZ = 25  # in calories
FOOD_3_CARBS_P4OZ = 4  # in grams
FOOD_3_PROTEIN_P4OZ = 0 # in grams
FOOD_3_TOTAL_FAT_P4OZ = 0 # in grams
FOOD_3_SODIUM_P4OZ = 550 # in milligrams


# food #4 intended constants
FOOD_4_NAME = "monterey jack cheese"
FOOD_4_CALORIES_P4OZ = 110 # in calories
FOOD_4_CARBS_P4OZ = 1 # in grams
FOOD_4_PROTEIN_P4OZ = 6 # in grams
FOOD_4_TOTAL_FAT_P4OZ = 8 # in grams
FOOD_4_SODIUM_P4OZ = 190 # in milligrams


# food #5 intended constants
FOOD_5_NAME = "sour cream"
FOOD_5_CALORIES_P4OZ = 110 # in calories
FOOD_5_CARBS_P4OZ = 2 # in grams
FOOD_5_PROTEIN_P4OZ = 2 # in grams
FOOD_5_TOTAL_FAT_P4OZ = 9 # in grams
FOOD_5_SODIUM_P4OZ = 30 # in milligrams


# food #6 intended constants
FOOD_6_NAME = "romaine lettuce"
FOOD_6_CALORIES_P4OZ = 5 # in calories
FOOD_6_CARBS_P4OZ = 1 # in grams
FOOD_6_PROTEIN_P4OZ = 0 # in grams
FOOD_6_TOTAL_FAT_P4OZ = 0 # in grams
FOOD_6_SODIUM_P4OZ = 0 # in milligrams

INDENT = "   "
OZ_TO_G_MULTIPLIER = 28.35
error_msg = "Error! Please try again and enter a number between 0 and 1500"
error_msg_two = "Error! Please try again and enter a number between 0 and 15"

# initialize accumlator variables
total_carbs = 0.
total_cals = 0
total_protein = 0
total_fat = 0
total_sodium = 0

# print menu
print("---------- List of Possible Ingredients ---------")
print(INDENT + "Food #1: " + FOOD_1_NAME)
print(INDENT + "Food #2: " + FOOD_2_NAME)
print(INDENT + "Food #2: " + FOOD_3_NAME)
print(INDENT + "Food #2: " + FOOD_4_NAME)
print(INDENT + "Food #2: " + FOOD_5_NAME)
print(INDENT + "Food #4: " + FOOD_6_NAME + "\n")

# name of recipe
recipe_name = input("What are you calling this recipe? ")

# food #1 ---------------------------------------------------------
user_input_int = int(input("How many grams of " + FOOD_1_NAME + "? "))
if user_input_int >= 0 and user_input_int <= 1500:
   # update accumulators
   total_cals += user_input_int * ((FOOD_1_CALORIES_P4OZ*OZ_TO_G_MULTIPLIER) /
                                 100.)
   total_carbs += user_input_int * ((
                                        FOOD_1_CARBS_P4OZ*OZ_TO_G_MULTIPLIER)
                                     / 100.)
   total_protein += user_input_int * ((FOOD_1_PROTEIN_P4OZ*OZ_TO_G_MULTIPLIER)
                                   /100)
   total_fat += user_input_int * ((FOOD_1_TOTAL_FAT_P4OZ*OZ_TO_G_MULTIPLIER)
                                 /100)
   total_sodium += user_input_int * ((FOOD_1_SODIUM_P4OZ*OZ_TO_G_MULTIPLIER)
                                    /100)
else:
   sys.exit(error_msg)
# food #2 ---------------------------------------------------------
user_input_int = int(input("How many grams of " + FOOD_2_NAME + "? "))
if user_input_int >= 0 and user_input_int <= 1500:
   # update accumulators
   total_cals += user_input_int * ((FOOD_2_CALORIES_P4OZ*OZ_TO_G_MULTIPLIER) /
                                100.)
   total_carbs += user_input_int * ((
                                        FOOD_2_CARBS_P4OZ*OZ_TO_G_MULTIPLIER)
                                     / 100.)
   total_protein += user_input_int * ((FOOD_2_PROTEIN_P4OZ*OZ_TO_G_MULTIPLIER)
                                   /100)
   total_fat += user_input_int * ((FOOD_2_TOTAL_FAT_P4OZ*OZ_TO_G_MULTIPLIER)
                                 /100)
   total_sodium += user_input_int * ((FOOD_2_SODIUM_P4OZ*OZ_TO_G_MULTIPLIER)
                                    /100)
else:
   sys.exit(error_msg)
# food #3 ---------------------------------------------------------
user_input_int = int(input("How many grams of " + FOOD_3_NAME + "? "))
if user_input_int >= 0 and user_input_int <= 1500:
   # update accumulators
   total_cals += user_input_int * ((FOOD_3_CALORIES_P4OZ*OZ_TO_G_MULTIPLIER) /
                                100.)
   total_carbs += user_input_int * ((
                                        FOOD_3_CARBS_P4OZ*OZ_TO_G_MULTIPLIER)
                                     / 100.)
   total_protein += user_input_int * ((FOOD_3_PROTEIN_P4OZ*OZ_TO_G_MULTIPLIER)
                                   /100)
   total_fat += user_input_int * ((FOOD_3_TOTAL_FAT_P4OZ*OZ_TO_G_MULTIPLIER)
                                 /100)
   total_sodium += user_input_int * ((FOOD_3_SODIUM_P4OZ*OZ_TO_G_MULTIPLIER)
                                    /100)
else:
   sys.exit(error_msg)
# food #4 ---------------------------------------------------------
user_input_int = int(input("How many grams of " + FOOD_4_NAME + "? "))
if user_input_int >= 0 and user_input_int <= 1500:
   # update accumulators
   total_cals += user_input_int * ((FOOD_4_CALORIES_P4OZ*OZ_TO_G_MULTIPLIER) /
                                100.)
   total_carbs += user_input_int * ((
                                        FOOD_4_CARBS_P4OZ*OZ_TO_G_MULTIPLIER)
                                     / 100.)
   total_protein += user_input_int * ((FOOD_4_PROTEIN_P4OZ*OZ_TO_G_MULTIPLIER)
                                   /100)
   total_fat += user_input_int * ((FOOD_4_TOTAL_FAT_P4OZ*OZ_TO_G_MULTIPLIER)
                                 /100)
   total_sodium += user_input_int * ((FOOD_4_SODIUM_P4OZ*OZ_TO_G_MULTIPLIER)
                                    /100)
else:
   sys.exit(error_msg)
# food #5 ---------------------------------------------------------
user_input_int = int(input("How many grams of " + FOOD_5_NAME + "? "))
if user_input_int >= 0 and user_input_int <= 1500:
   # update accumulators
   total_cals += user_input_int * ((FOOD_5_CALORIES_P4OZ*OZ_TO_G_MULTIPLIER) /
                                100.)
   total_carbs += user_input_int * ((
                                        FOOD_5_CARBS_P4OZ*OZ_TO_G_MULTIPLIER)
                                     / 100.)
   total_protein += user_input_int * ((FOOD_5_PROTEIN_P4OZ*OZ_TO_G_MULTIPLIER)
                                   /100)
   total_fat += user_input_int * ((FOOD_5_TOTAL_FAT_P4OZ*OZ_TO_G_MULTIPLIER)
                                 /100)
   total_sodium += user_input_int * ((FOOD_5_SODIUM_P4OZ*OZ_TO_G_MULTIPLIER)
                                    /100)
else:
   sys.exit(error_msg)
# food #6 ---------------------------------------------------------
user_input_int = int(input("How many grams of " + FOOD_6_NAME + "? "))
if user_input_int >= 0 and user_input_int <= 1500:
   # update accumulators
   total_cals += user_input_int * ((FOOD_6_CALORIES_P4OZ*OZ_TO_G_MULTIPLIER) /
                                100.)
   total_carbs += user_input_int * ((
                                        FOOD_6_CARBS_P4OZ*OZ_TO_G_MULTIPLIER)
                                     / 100.)
   total_protein += user_input_int * ((FOOD_6_PROTEIN_P4OZ*OZ_TO_G_MULTIPLIER)
                                   /100)
   total_fat += user_input_int * ((FOOD_6_TOTAL_FAT_P4OZ*OZ_TO_G_MULTIPLIER)
                                 /100)
   total_sodium += user_input_int * ((FOOD_6_SODIUM_P4OZ*OZ_TO_G_MULTIPLIER)
                                    /100)
else:
   sys.exit(error_msg)
# servings -----------------------------------------------------------------
servings = int(input("How many servings?"))
if servings >= 0 and servings <= 15:
   total_cals *= servings
   total_carbs *= servings
   total_protein *= servings
   total_fat *= servings
   total_sodium *= servings
else:
   sys.exit(error_msg_two)
# report results --------------------------------------------------
print("\nNutrition for " + recipe_name + "------------")
print(INDENT + "Calories: " + str(total_cals))
print(INDENT + "Carbohydrates: " + str(total_carbs) + " grams")
print(INDENT + "Protein: " + str(total_protein) + " grams")
print(INDENT + "Total fat: " + str(total_fat) + " grams")
print(INDENT + "Sodium: " + str(total_sodium) + " milligrams\n")
print()