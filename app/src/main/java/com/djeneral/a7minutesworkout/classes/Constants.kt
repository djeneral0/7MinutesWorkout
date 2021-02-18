package com.djeneral.a7minutesworkout.classes

import com.djeneral.a7minutesworkout.R

class Constants{
    companion object{
        fun defaultExerciseList(): ArrayList<ExerciseModel>{
            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks = ExerciseModel(
                    1,
                    "Jumping Jacks",
                    R.drawable.ic_jumping_jacks)
            exerciseList.add(jumpingJacks)

            val wallSit = ExerciseModel(
                    2,
                    "Wall Sit",
                    R.drawable.ic_wall_sit)
            exerciseList.add(jumpingJacks)
            exerciseList.add(wallSit)

            val pushUp = ExerciseModel(
                    3,
                    "Push Up",
                    R.drawable.ic_push_up)
            exerciseList.add(pushUp)

            val adominal = ExerciseModel(
                    4,
                    "Abdominal Crunch",
                    R.drawable.ic_abdominal_crunch)
            exerciseList.add(adominal)

            val stepUpOnchair = ExerciseModel(
                    5,
                    "Step-Up onto Chair",
                    R.drawable.ic_step_up_onto_chair)
            exerciseList.add(stepUpOnchair)

            val squat = ExerciseModel(
                    6,
                    "Squat",
                    R.drawable.ic_squat)
            exerciseList.add(squat)

            val trice = ExerciseModel(
                    7,
                    "Triceps Dip On Chair",
                    R.drawable.ic_triceps_dip_on_chair)
            exerciseList.add(trice)

            val plank = ExerciseModel(
                    8,
                    "Plank",
                    R.drawable.ic_plank)
            exerciseList.add(plank)

            val highKnees = ExerciseModel(
                    9,
                    "High Knees Running in Place",
                    R.drawable.ic_high_knees_running_in_place)
            exerciseList.add(highKnees)

            val lunges = ExerciseModel(
                    10,
                    "Lunges",
                    R.drawable.ic_lunge)
            exerciseList.add(lunges)

            val pushAndRotate = ExerciseModel(
                    11,
                    "Push up and Rotation",
                    R.drawable.ic_push_up_and_rotation)
            exerciseList.add(pushAndRotate)

            val sidePlank = ExerciseModel(
                    12,
                    "Side Plank",
                    R.drawable.ic_jumping_jacks)
            exerciseList.add(sidePlank)
            return exerciseList
        }
    }
}