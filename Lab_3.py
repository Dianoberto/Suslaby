import numpy as np
import matplotlib.pyplot as plt

class MultiAgentEnvironment:
    def __init__(self, num_agents, environment_size):
        self.num_agents = num_agents
        self.environment_size = environment_size
        self.agents_positions = np.zeros((num_agents, 2), dtype=int)
        self.goals_positions = np.random.randint(0, environment_size, size=(num_agents, 2))
        self.steps_taken = np.zeros(num_agents, dtype=int)

    def reset(self):
        self.agents_positions = np.zeros((self.num_agents, 2), dtype=int)
        self.goals_positions = np.random.randint(0, self.environment_size, size=(self.num_agents, 2))
        self.steps_taken = np.zeros(self.num_agents, dtype=int)

    def step(self, actions):
        rewards = np.zeros(self.num_agents)

        for i in range(self.num_agents):
            action = actions[i]
            if action == 0:  # Вгору
                self.agents_positions[i, 0] = max(0, self.agents_positions[i, 0] - 1)
            elif action == 1:  # Вниз
                self.agents_positions[i, 0] = min(self.environment_size - 1, self.agents_positions[i, 0] + 1)
            elif action == 2:  # Вліво
                self.agents_positions[i, 1] = max(0, self.agents_positions[i, 1] - 1)
            elif action == 3:  # Вправо
                self.agents_positions[i, 1] = min(self.environment_size - 1, self.agents_positions[i, 1] + 1)

            # Розрахунок винагороди
            distance_to_goal = np.abs(self.agents_positions[i, 0] - self.goals_positions[i, 0]) + \
                               np.abs(self.agents_positions[i, 1] - self.goals_positions[i, 1])
            rewards[i] = distance_to_goal  # Мінімізація відстані до цілі, тобто максимізація позитивної винагороди

            self.steps_taken[i] += 1

        return rewards

# Створення мультиагентного середовища та тестування
num_agents = 15
env_size = 5
multi_agent_env = MultiAgentEnvironment(num_agents, env_size)

# Збереження результатів тестувань для подальшого аналізу
episode_rewards = []

for episode in range(70):
    multi_agent_env.reset()
    total_rewards = np.zeros(num_agents)

    while np.any(multi_agent_env.steps_taken < 70):  # Допустимо 50 кроків для кожного агента
        actions = np.random.randint(0, 4, size=num_agents)  # Випадкові дії для тестування
        rewards = multi_agent_env.step(actions)
        total_rewards += rewards

    episode_rewards.append(total_rewards)
    print(f"Episode {episode + 1}, Total Rewards: {total_rewards}")

# Аналіз результатів та візуалізація
average_rewards = np.mean(episode_rewards, axis=0)

plt.plot(range(1, num_agents + 1), average_rewards, marker='o')
plt.xlabel('Agent')
plt.ylabel('Average Total Reward')
plt.title('Average Total Reward per Agent over Episodes')
plt.show()
