close all;

path = "FM-LAB4" + filesep + "ex2" + filesep;
filename = "FM-LAB4" + filesep + "data.csv";
data = readtable(filename);

time = data.x_DATE_;
price = data.x_CLOSE_;

date_str = num2str(time);
date_str = strcat(date_str(:,1:2), '/', date_str(:,3:4), '/', date_str(:,5:6));  % Преобразуем дату в строку
dates = datetime(date_str, 'InputFormat', 'yy/MM/dd');  % Преобразуем в формат datetime

T_values = [1, 7, 30, 90, 365];  % 1 день, 1 неделя, 1 месяц, 3 месяца, 1 год
periods = {'1 день', '1 неделю', '1 месяц', '3 месяца', '1 год'};

for i = 1:length(T_values)
    h = figure;
    T = T_values(i);

    W = tf(1, [T, 1]);

    initial_value = price(1);  % Начальное значение фильтра = первое значение цены

    % Пропускание сигнала через фильтр
    [filtered_signal, ~] = lsim(W, price - initial_value, 1:length(price));

    % Восстановление исходного значения
    filtered_signal = filtered_signal + initial_value;

    % Настройка стиля графиков
    set(h, 'Color', '#1e1e1e');  % Темный фон (почти черный)

    % Плотная настройка окна (не квадратное)
    set(h, 'Position', [100, 100, 1200, 600]);  % Прямоугольное окно, шире чем высокое

    % График исходных данных
    plot(dates, price, 'r', 'LineWidth', 2, 'LineJoin', 'round'); hold on;
    
    % График фильтрованных данных
    plot(dates, filtered_signal, 'b', 'LineWidth', 2, 'LineJoin', 'round'); 

    % Названия и легенда
    % title("Сглаживание за " + periods{i} + ", T = " + string(T), 'Color', 'w', 'FontSize', 16, 'FontWeight', 'bold');
    xlabel('Дата', 'Color', 'w', 'FontSize', 14);
    ylabel('Цена акции', 'Color', 'w', 'FontSize', 14);
    
    % Легенда с полупрозрачным фоном
    lgd = legend('Исходный сигнал', 'Фильтрованный сигнал', 'Location', 'northeast');
    set(lgd, 'TextColor', 'w', 'Color', [0.1, 0.1, 0.1], 'Box', 'off');

    % Сетка
    grid on;
    ax = gca;
    ax.XColor = 'w';  % Белые оси
    ax.YColor = 'w';  % Белые оси
    ax.GridColor = [0.3, 0.3, 0.3];  % Темная сетка
    ax.GridAlpha = 0.4;  % Прозрачность сетки

    % Убираем вертикальные линии сетки, чтобы они не были слишком навязчивыми
    set(ax, 'MinorGridLineStyle', '-');

    % Стиль для линий: сглаживание
    set(gca, 'LineWidth', 1.5);
    
    % Увеличиваем границы для большей видимости
    ylim([min(price) - 50, max(price) + 50]); 

    % Сохранение изображения
    saveas(h, path + string(T) + '.png', 'png');
end
