% clear all;
close all;

t = (0:0.01:10)';
u = [t, ones(size(t))]; % входное воздействие -- функция Хевисайда

[~, scriptName] = fileparts(mfilename('fullpath'));
if ~isfolder(scriptName)
    mkdir(scriptName);
end

lambdas_all = [-1, -1, -1;
              -1, -1, -10;
              -1, -20, -10;
              -15, -20, -10;
              -1+1j, -1-1j, -1;
              -1+1j, -1-1j, -5;
              -1+3j, -1-3j, -5;
              -3+3j, -3-3j, -5;
              -1+1j, -1-1j, -15];

for i = 1:size(lambdas_all, 1)
    lambda1 = lambdas_all(i, 1);
    lambda2 = lambdas_all(i, 2);
    lambda3 = lambdas_all(i, 3);
    
    fig_complex = figure('Units', 'pixels', 'Position', [100 100 600 500]);
    hold on; grid on; box on;

    plot(real(lambda1), imag(lambda1), 'bo', 'MarkerFaceColor', 'b');
    plot(real(lambda2), imag(lambda2), 'red', 'Marker', 'square', 'MarkerFaceColor', 'red', 'LineStyle', 'none');
    plot(real(lambda3), imag(lambda3), 'black', 'Marker', 'pentagram', 'MarkerFaceColor', 'black', 'LineStyle', 'none');
    xlabel('Re'); ylabel('Im');

    plot([-100 100], [0 0], 'black', LineWidth=1.2)
    plot([0 0], [-100 100], 'black', LineWidth=1.2)
    legend('$\lambda_1$', '$\lambda_2$', '$\lambda_3$', 'Interpreter', 'latex', 'FontSize', 10);
    if (min(imag(lambdas_all(i, :))) ~= 0)
        ylim([min(-2, min(imag(lambdas_all(i, :)))-abs(min(imag(lambdas_all(i, :))))/4), max(2, max(imag(lambdas_all(i, :)))+abs(max(imag(lambdas_all(i, :))))/4)])
    else
        ylim([min(-2, min(real(lambdas_all(i, :)))-abs(min(real(lambdas_all(i, :)))/4)), max(2, max(real(lambdas_all(i, :)))+abs(max(real(lambdas_all(i, :))))/4)])
    end
    xlim([min(-2, min(real(lambdas_all(i, :)))-abs(min(real(lambdas_all(i, :))))/4), max(2, max(real(lambdas_all(i, :)))+abs(max(real(lambdas_all(i, :))))/4)])

    num = [abs(lambda3*lambda2*lambda1)];
    den = poly([lambda1, lambda2, lambda3]);
    sys = tf(num, den);
    y = lsim(sys, u(:,2), t);
    info = stepinfo(y, t);
    
    fig_main = figure('Units', 'pixels', 'Position', [100 100 600 500]);
    plot(t, y, LineWidth=1.3, Color='blue')
    grid on;
    title('Step Response')
    xlabel('Время (секунды)'), ylabel('Амплитуда')
    if (max(y) <= 1)
        ylim([0, 1.4])
    end
    hold on;
    y_final = info.SettlingMin + (info.SettlingMax - info.SettlingMin)/2; % среднее значение
    plot([info.SettlingTime info.SettlingTime], ylim, 'k--'); % вертикальная линия
    
    y_settle = interp1(t, y, info.SettlingTime);
    y_steady = y(end);               % установившееся значение
    y_difference = abs(y_steady - y_settle); % 0.02
    y_max = max(y);                  % максимум отклика
    yline(y_steady-y_difference, 'k-.'); % нижняя граница окрестности
    yline(y_steady+y_difference, 'k-.'); % верхняя граница окрестности
    sigma = abs(y_max - y_steady) / abs(y_steady) * 100;  % в процентах

    text('Units', 'normalized', ...
        'Position', [0.44 0.9], ...
        'String', sprintf('Время переходного процесса: %.2f s\nПеререгулирование: %.2f%%', info.SettlingTime, sigma), ...
        'BackgroundColor', 'w', ...
        'EdgeColor', 'k');

    % Отображаем точку
    plot(info.SettlingTime, y_settle, 'blacko', 'MarkerFaceColor', 'black', 'MarkerSize', 4);
    
    hold off;
    set(fig_complex, 'PaperUnits', 'inches', 'PaperPosition', [0 0 6 5]);
    saveas(fig_complex, string(scriptName) + '\complex_plan_' + string(lambda1) + '_' + string(lambda2) + '_' + string(lambda3) + '.eps', 'epsc')
    print(fig_main, '-depsc', string(scriptName) + '\' + string(lambda1) + '_' + string(lambda2) + '_' + string(lambda3) + '.eps')
    
end