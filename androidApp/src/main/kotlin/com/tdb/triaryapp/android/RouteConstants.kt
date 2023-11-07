package com.tdb.triaryapp.android

class RouteConstants {
    class Main {
        companion object {
            const val MAIN = "main"
        }
    }

    class Local {
        companion object {
            const val LOCAL = "local"
        }
        class Tabs {
            companion object {
                const val TABS = "$LOCAL/tabs"
            }
            class Create {
                companion object {
                    const val CREATE_POWER = "$TABS/create_power"
                    const val CREATE_CARDIO = "$TABS/create_cardio"
                }
            }
            class Edit {
                companion object {
                    const val EDIT_POWER_ROUTE = "$TABS/edit_power/{trainingId}"
                    const val EDIT_CARDIO_ROUTE = "$TABS/edit_cardio/{trainingId}"
                    const val EDIT_POWER = "$TABS/edit_power/"
                    const val EDIT_CARDIO = "$TABS/edit_cardio/"
                }
            }
            class PowerTraining {
                companion object {
                    const val POWER_TRAINING = "$TABS/power"
                }
                class Exercises {
                    companion object {
                        const val EXERCISES = "$POWER_TRAINING/exercises"
                    }
                    class Exercise {
                        companion object {
                            const val EXERCISE = "$EXERCISES/{exerciseId}"
                        }
                    }
                }
                class Packs {
                    companion object {
                        const val PACKS = "$POWER_TRAINING/packs"
                    }
                    class Pack {
                        companion object {
                            const val PACK = "$PACKS/{packId}"
                        }
                    }
                }
                class Dates {
                    companion object {
                        const val DATES = "$POWER_TRAINING/dates"
                    }
                    class Date {
                        companion object {
                            const val DATE = "$DATES/{dateId}"
                        }
                    }
                }
            }
        }
    }
}