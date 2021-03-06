package com.ava.foodlogger.domain;

import com.ava.foodlogger.domain.enumeration.ActivityLevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * A AppUser.
 */
@Entity
@Table(name = "app_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "bio")
    private String bio;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @NotNull
    @Column(name = "height", nullable = false)
    private Float height;

    @Column(name = "workouts_per_week")
    private Integer workoutsPerWeek;

    @Column(name = "minutes_per_workout")
    private Integer minutesPerWorkout;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_level")
    private ActivityLevel activityLevel;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "foodDay", "user", "foods" }, allowSetters = true)
    private Set<FoodEntry> foodEntries = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "foodEntries", "user" }, allowSetters = true)
    private Set<FoodDay> foodDays = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "user" }, allowSetters = true)
    private Set<CurrentWeight> currentWeights = new HashSet<>();

    @OneToMany(mappedBy = "user")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "user" }, allowSetters = true)
    private Set<GoalWeight> goalWeights = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser id(Long id) {
        this.id = id;
        return this;
    }

    public String getBio() {
        return this.bio;
    }

    public AppUser bio(String bio) {
        this.bio = bio;
        return this;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public AppUser createdDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Float getHeight() {
        return this.height;
    }

    public AppUser height(Float height) {
        this.height = height;
        return this;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Integer getWorkoutsPerWeek() {
        return this.workoutsPerWeek;
    }

    public AppUser workoutsPerWeek(Integer workoutsPerWeek) {
        this.workoutsPerWeek = workoutsPerWeek;
        return this;
    }

    public void setWorkoutsPerWeek(Integer workoutsPerWeek) {
        this.workoutsPerWeek = workoutsPerWeek;
    }

    public Integer getMinutesPerWorkout() {
        return this.minutesPerWorkout;
    }

    public AppUser minutesPerWorkout(Integer minutesPerWorkout) {
        this.minutesPerWorkout = minutesPerWorkout;
        return this;
    }

    public void setMinutesPerWorkout(Integer minutesPerWorkout) {
        this.minutesPerWorkout = minutesPerWorkout;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public AppUser dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ActivityLevel getActivityLevel() {
        return this.activityLevel;
    }

    public AppUser activityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
        return this;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public User getUser() {
        return this.user;
    }

    public AppUser user(User user) {
        this.setUser(user);
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<FoodEntry> getFoodEntries() {
        return this.foodEntries;
    }

    public AppUser foodEntries(Set<FoodEntry> foodEntries) {
        this.setFoodEntries(foodEntries);
        return this;
    }

    public AppUser addFoodEntry(FoodEntry foodEntry) {
        this.foodEntries.add(foodEntry);
        foodEntry.setUser(this);
        return this;
    }

    public AppUser removeFoodEntry(FoodEntry foodEntry) {
        this.foodEntries.remove(foodEntry);
        foodEntry.setUser(null);
        return this;
    }

    public void setFoodEntries(Set<FoodEntry> foodEntries) {
        if (this.foodEntries != null) {
            this.foodEntries.forEach(i -> i.setUser(null));
        }
        if (foodEntries != null) {
            foodEntries.forEach(i -> i.setUser(this));
        }
        this.foodEntries = foodEntries;
    }

    public Set<FoodDay> getFoodDays() {
        return this.foodDays;
    }

    public AppUser foodDays(Set<FoodDay> foodDays) {
        this.setFoodDays(foodDays);
        return this;
    }

    public AppUser addFoodDay(FoodDay foodDay) {
        this.foodDays.add(foodDay);
        foodDay.setUser(this);
        return this;
    }

    public AppUser removeFoodDay(FoodDay foodDay) {
        this.foodDays.remove(foodDay);
        foodDay.setUser(null);
        return this;
    }

    public void setFoodDays(Set<FoodDay> foodDays) {
        if (this.foodDays != null) {
            this.foodDays.forEach(i -> i.setUser(null));
        }
        if (foodDays != null) {
            foodDays.forEach(i -> i.setUser(this));
        }
        this.foodDays = foodDays;
    }

    public Set<CurrentWeight> getCurrentWeights() {
        return this.currentWeights;
    }

    public AppUser currentWeights(Set<CurrentWeight> currentWeights) {
        this.setCurrentWeights(currentWeights);
        return this;
    }

    public AppUser addCurrentWeight(CurrentWeight currentWeight) {
        this.currentWeights.add(currentWeight);
        currentWeight.setUser(this);
        return this;
    }

    public AppUser removeCurrentWeight(CurrentWeight currentWeight) {
        this.currentWeights.remove(currentWeight);
        currentWeight.setUser(null);
        return this;
    }

    public void setCurrentWeights(Set<CurrentWeight> currentWeights) {
        if (this.currentWeights != null) {
            this.currentWeights.forEach(i -> i.setUser(null));
        }
        if (currentWeights != null) {
            currentWeights.forEach(i -> i.setUser(this));
        }
        this.currentWeights = currentWeights;
    }

    public Set<GoalWeight> getGoalWeights() {
        return this.goalWeights;
    }

    public AppUser goalWeights(Set<GoalWeight> goalWeights) {
        this.setGoalWeights(goalWeights);
        return this;
    }

    public AppUser addGoalWeight(GoalWeight goalWeight) {
        this.goalWeights.add(goalWeight);
        goalWeight.setUser(this);
        return this;
    }

    public AppUser removeGoalWeight(GoalWeight goalWeight) {
        this.goalWeights.remove(goalWeight);
        goalWeight.setUser(null);
        return this;
    }

    public void setGoalWeights(Set<GoalWeight> goalWeights) {
        if (this.goalWeights != null) {
            this.goalWeights.forEach(i -> i.setUser(null));
        }
        if (goalWeights != null) {
            goalWeights.forEach(i -> i.setUser(this));
        }
        this.goalWeights = goalWeights;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppUser)) {
            return false;
        }
        return id != null && id.equals(((AppUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppUser{" +
            "id=" + getId() +
            ", bio='" + getBio() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", height=" + getHeight() +
            ", workoutsPerWeek=" + getWorkoutsPerWeek() +
            ", minutesPerWorkout=" + getMinutesPerWorkout() +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", activityLevel='" + getActivityLevel() + "'" +
            "}";
    }
}
