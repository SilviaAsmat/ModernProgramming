public class LevelManager 
{
    private final LevelStatsTracker basic = new LevelStatsTracker();
    private final LevelStatsTracker intermediate = new LevelStatsTracker();
    private final LevelStatsTracker advanced = new LevelStatsTracker();
    private LevelType currentLevel = LevelType.BASIC;

    public LevelManager() 
    {

    }

    public void onGotAnswerCorrect(boolean isFirstTry)
    {
        switch (currentLevel) {
            case BASIC -> basic.gotAnswerCorrect(isFirstTry);
            case INTERMEDIATE -> intermediate.gotAnswerCorrect(isFirstTry);
            case ADVANCED -> advanced.gotAnswerCorrect(isFirstTry);
        }
    }
    
    public void onGotAnswerWrong()
    {
        switch (currentLevel) {
            case BASIC -> basic.gotAnswerWrong();
            case INTERMEDIATE -> intermediate.gotAnswerWrong();
            case ADVANCED -> advanced.gotAnswerWrong();
        }
    }

    public String getLevelDisplayName() 
    {
        String displayName = "";
        switch (currentLevel) {
            case BASIC -> displayName = "basic";
            case INTERMEDIATE -> displayName = "intermediate";
            case ADVANCED -> displayName = "advanced";
        }
        return displayName;
    }

    public boolean isUserEligibleForNextLevel()
    {
        boolean isEligible = false;
        switch (currentLevel) {
            case BASIC -> isEligible = basic.isUserEligibleForNextLevel();
            case INTERMEDIATE -> isEligible = intermediate.isUserEligibleForNextLevel();
            case ADVANCED -> isEligible = false;
        }
        return isEligible;
    }

    public void moveToNextLevel()
    {
        switch (currentLevel) {
            case BASIC -> currentLevel = LevelType.INTERMEDIATE;
            case INTERMEDIATE -> currentLevel = LevelType.ADVANCED;
            case ADVANCED -> currentLevel = LevelType.ADVANCED;
        }
    }

    public int getNumberOfOperatorsForCurrentLevel()
    {
        int numOfOperators = 0;
        switch (currentLevel) {
            case BASIC -> numOfOperators = 1;
            case INTERMEDIATE -> numOfOperators = 2;
            case ADVANCED -> numOfOperators = 3;
        }
        return numOfOperators;
    }

    public String displayLevelStats()
    {
        StringBuilder grades = new StringBuilder();
        grades.append(String.format("Basic: %.2f", basic.getPercentCorrect()));
        if(!intermediate.isEmpty())
        {
            grades.append(String.format("\nIntermediate: %.2f", intermediate.getPercentCorrect()));
        }

        if(!advanced.isEmpty())
        {
            grades.append(String.format("\nAdvanced: %.2f", advanced.getPercentCorrect()));
        }

        if(basic.getPercentCorrect() < 80)
        {
            grades.append("\nPlease  ask  your  teacher  for  extra help");   
        }
        return grades.toString();
    }
}
