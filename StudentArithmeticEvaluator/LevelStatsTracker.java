public class LevelStatsTracker {
    private double numberOfAnswersCorrect = 0;
    private double numberOfAnswersWrong = 0;
    private int gotAnswersRightOnFirstTry = 0;

    public void gotAnswerCorrect(boolean isFirstTry)
    {
        numberOfAnswersCorrect++;
        if(isFirstTry)
        {
            gotAnswersRightOnFirstTry++;
        }
    }

    public void gotAnswerWrong()
    {
        numberOfAnswersWrong++;
    }

    public Double getPercentCorrect()
    {
        return (numberOfAnswersCorrect/(numberOfAnswersWrong+numberOfAnswersCorrect)) * 100;
    }

    public boolean isUserEligibleForNextLevel()
    {
        return gotAnswersRightOnFirstTry >= 5; //TODO: Move hardcoded 5 to constructor param
    }

    public boolean isEmpty()
    {
        return numberOfAnswersCorrect + numberOfAnswersWrong == 0;
    }
}
