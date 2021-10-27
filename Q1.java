class Q1 {
    public static void main(String args[]) {
        SimpleReflexAgent agent = new SimpleReflexAgent();
        agent.tryAllCases();
    }
}

class SimpleReflexAgent {
    private void dirtConf(Environment env) {
        String action = "";
        int performance_measure_score = 0;
        System.out.println("\n.................");
        System.out.println("Environment:\nInitial Position of agent: " + env.position + "\nDirt configaration: " + env.conf_A + " " + env.conf_B);
        System.out.print("Action: ");
        if(env.position == 'A') {
            if(env.conf_A.equals("dirt")) {
                performance_measure_score++;
                action = "[Suck]";
            }
            if(env.conf_B.equals("dirt")) {
                action += "[Right Suck]";
                performance_measure_score++;
            }

        } else if(env.position == 'B') {
            if(env.conf_B.equals("dirt")) {
                action = "[Suck]";
                performance_measure_score++;
            }
            if(env.conf_A.equals("dirt")) {
                action += "[Left Suck]";
                performance_measure_score++;
            }  
        }
        System.out.println(action + "\nPerformance measure score: " + performance_measure_score);
    }

    void tryAllCases() {
        //8 cases
        dirtConf(new Environment('A', "clean", "clean"));
        dirtConf(new Environment('A', "clean", "dirt"));
        dirtConf(new Environment('A', "dirt", "clean"));
        dirtConf(new Environment('A', "dirt", "dirt"));
        dirtConf(new Environment('B', "clean", "clean"));
        dirtConf(new Environment('B', "clean", "dirt"));
        dirtConf(new Environment('B', "dirt", "clean"));
        dirtConf(new Environment('B', "dirt", "dirt"));
    }

    void stateSpaceSearchGraph() {
        
    }
}

class Environment {
    char position;
    String conf_A;
    String conf_B;
    Environment(char position, String conf_A, String conf_B) {
        this.position = position;
        this.conf_A = conf_A;
        this.conf_B = conf_B;
    }
}