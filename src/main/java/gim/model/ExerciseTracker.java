package gim.model;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.List;

import gim.model.exercise.Exercise;
import gim.model.exercise.ExerciseHashMap;
import gim.model.exercise.ExerciseList;
import gim.model.exercise.Name;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the exercise-tracker level.
 *
 * Duplicates are allowed.
 */
public class ExerciseTracker implements ReadOnlyExerciseTracker {

    private final ExerciseList exerciseList;
    private ExerciseHashMap exerciseHashMap;

    /**
     * Creates an ExerciseTracker
     */
    public ExerciseTracker() {
        exerciseList = new ExerciseList();
        exerciseHashMap = new ExerciseHashMap();
    }

    /**
     * Creates an ExerciseTracker using a given HashMap {@code ehm}
     * @param ehm ExerciseHashMap
     */
    public ExerciseTracker(ExerciseHashMap ehm) {
        exerciseList = new ExerciseList();
        exerciseHashMap = ehm;
    }

    /**
     * Creates an ExerciseTracker using the Exercises in the {@code toBeCopied}
     */
    public ExerciseTracker(ReadOnlyExerciseTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the ExerciseList with {@code exercises}.
     * {@code exercises} can contain duplicate Exercises.
     */
    public void setExercises(List<Exercise> exercises) {
        this.exerciseList.setExercises(exercises);
        this.exerciseHashMap.setExercises(exercises);
    }

    /**
     * Resets the existing data of this {@code ExerciseTracker} with {@code newData}.
     */
    public void resetData(ReadOnlyExerciseTracker newData) {
        requireNonNull(newData);

        setExercises(newData.getExerciseList());
    }

    //// exercise-level operations

    /**
     * Returns true if an Exercise with the same identity as {@code exercise} exists in the exercise tracker.
     */
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return exerciseHashMap.contains(exercise);
    }

    /**
     * Returns the Exercise with the highest weight, with Name {@code exercises}.
     * @param exerciseName Name of exercise.
     * @return Exercise containing the highest weight.
     */
    public Exercise getExercisePR(Name exerciseName) {
        return exerciseHashMap.getExercisePR(exerciseName);
    }

    /**
     * Adds an Exercise to the exercise tracker.
     * The Exercise can already exist in the exercise tracker.
     */
    public Exercise addExercise(Exercise p) {
        Exercise toAdd = exerciseHashMap.add(p);
        exerciseList.add(toAdd);
        return toAdd;
    }

    /**
     * Replaces the given Exercise {@code target} in the list with {@code editedExercise}.
     * {@code target} must exist in the exercise tracker.
     * The Exercise identity of {@code editedExercise} can be the same as another existing Exercise
     * in the exercise tracker.
     */
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireNonNull(editedExercise);

        exerciseList.setExercise(target, editedExercise);
    }

    /**
     * Removes {@code key} from this {@code ExerciseTracker}.
     * {@code key} must exist in the exercise tracker.
     */
    public void removeExercise(Exercise key) {
        exerciseHashMap.remove(key);
        exerciseList.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return exerciseList.asUnmodifiableObservableList().size() + " exercises";
        // TODO: refine later
    }

    @Override
    public ObservableList<Exercise> getExerciseList() {
        return exerciseList.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExerciseTracker // instanceof handles nulls
                && exerciseList.equals(((ExerciseTracker) other).exerciseList));
    }

    @Override
    public int hashCode() {
        return exerciseList.hashCode();
    }

    public ExerciseHashMap getExerciseHashMap() {
        return exerciseHashMap;
    }

}
