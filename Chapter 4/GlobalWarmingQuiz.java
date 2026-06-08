import java.util.Scanner;

public class GlobalWarmingQuiz {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] questions = {
            "Q1: Which gas is most associated with global warming?\n1) Oxygen\n2) Carbon dioxide\n3) Nitrogen\n4) Helium",
            "Q2: Which organization shared the 2007 Nobel Peace Prize with Al Gore?\n1) NASA\n2) Intergovernmental Panel on Climate Change (IPCC)\n3) WHO\n4) Greenpeace",
            "Q3: What do skeptics often argue?\n1) Climate change is exaggerated\n2) Climate change is caused only by humans\n3) Global warming is a myth\n4) The Earth is cooling",
            "Q4: Which renewable energy source helps reduce greenhouse gases?\n1) Coal\n2) Solar\n3) Oil\n4) Gasoline",
            "Q5: What is one major effect of global warming?\n1) Rising sea levels\n2) Lower oxygen\n3) More earthquakes\n4) Less sunlight"
        };

        int[] answers = {2, 2, 1, 2, 1}; // correct options
        int score = 0;

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.print("Your answer: ");
            int userAnswer = input.nextInt();
            if (userAnswer == answers[i]) {
                score++;
            }
        }

        System.out.println("\nYou scored " + score + " out of 5.");
        if (score == 5) {
            System.out.println("Excellent");
        } else if (score == 4) {
            System.out.println("Very good");
        } else {
            System.out.println("Time to brush up on your knowledge of global warming.");
            System.out.println("Check sources like: https://www.ipcc.ch or https://www.climate.gov");
        }
    }
}
