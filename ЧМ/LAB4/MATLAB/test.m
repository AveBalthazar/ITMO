close all;
% Параметры
T = 100;
b = 10;
c = 0;
d = 10;
a = 3;
dt = 0.001;
t = -T/2:dt:T/2;
V = 1/dt;
dv = 1/T;
v = -V/2:dv:V/2;

% Сигнал g(t)
g = zeros(size(t));
g(t >= -2 & t <= 2) = a;

% Белый шум
xi = 2*rand(size(t)) - 1;

% Зашумлённый сигнал u(t)
u = g + b*xi + c*sin(d*t);

% Передаточная функция фильтра
W1 = tf([1], [T, 1]);

% Пропускание сигнала через фильтр
[filtered_signal, t_out] = lsim(W1, u, t);

% Преобразование Фурье
G = fft(g);
U = fft(u);
Filtered = fft(filtered_signal);

% Масштабирование для отображения амплитуды
G = abs(G) / length(t);
U = abs(U) / length(t);
Filtered = abs(Filtered) / length(t);

% Сравнение спектров
figure;
subplot(2, 1, 1);
plot(v, G(1:length(v)), 'r', 'LineWidth', 1.5); hold on;
plot(v, U(1:length(v)), 'b', 'LineWidth', 1.5); hold on;
plot(v, Filtered(1:length(v)), 'g', 'LineWidth', 1.5);
xlim([-3, 3])
legend('G(t)', 'U(t)', 'Filtered U(t)');
title('Спектры сигналов');
xlabel('Частота (Гц)');
ylabel('Амплитуда');

% Частотная характеристика фильтра W_1(omega)
omega = 2*pi*v;
H_w = 1 ./ (1 + 1i*T*omega);

% Модуль произведения фильтра и спектра u(t)
Filtered_theoretical = H_w .* U;

% График модуля произведения фильтра и спектра
subplot(2, 1, 2);
xlim([-3, 3])
plot(v, abs(Filtered_theoretical(1:length(v))), 'k', 'LineWidth', 1.5);
xlim([-3, 3])
title('Модуль произведения фильтра и спектра u(t)');
xlabel('Частота (Гц)');
ylabel('Амплитуда');

% АЧХ фильтра
figure;
plot(v, abs(H_w), 'm', 'LineWidth', 1.5);
xlim([-3, 3])
title('Амплитудно-частотная характеристика фильтра');
xlabel('Частота (Гц)');
ylabel('Амплитуда');
