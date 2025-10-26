close all;
T2 = linspace(0, 5, 500);
Kcrit = (0.4 + T2)./(0.4*T2);

T2min = -2; T2max = 5;
Kmin = -2; Kmax = 20;

figure; hold on;
fill([T2min T2max T2max T2min], [Kmin Kmin Kmax Kmax], ...
     [0.9 0.7 0.7], 'EdgeColor','none', 'DisplayName','неустойчиво');
fill([T2 fliplr(T2)], ...
     [0*ones(size(T2)) fliplr(min(Kcrit,Kmax))], ...
     [0.7 0.9 0.7], 'EdgeColor','none', 'DisplayName','устойчиво асимптотически');

plot([0 0], [20 -2], 'k', 'Color', 'red', 'LineWidth', 1.4, 'DisplayName','не существует');

plot(T2, Kcrit, 'k', 'LineWidth', 1.2, 'DisplayName','верхняя граница, K = (0.4+T)/(0.4T)');
plot([-2 5], [0 0], 'k', 'LineWidth', 1.2, 'DisplayName','нижняя граница, K = 0');
xlim([T2min T2max]); ylim([Kmin Kmax]);
xlabel('T'); ylabel('K');
legend();
grid on;