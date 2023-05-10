# Библиотека для обработки естественного языка
import nltk
import string
# Используется для извлечения признаков из текста с использованием метода TF-IDF (Term Frequency-Inverse Document Frequency).
# Он преобразует тексты в векторы признаков, учитывая важность каждого слова в контексте всего текста.
from sklearn.feature_extraction.text import TfidfVectorizer
# Используется для разделения данных на обучающую и тестовую выборки.
from sklearn.model_selection import train_test_split
# Классификатор на основе метода опорных векторов (SVM)
from sklearn.svm import LinearSVC

# Загружаем обучающий набор данных, содержащий тексты на разных языках
def load_data():
    # Для примера, четыре языка: английский, испанский, французский и русский
    english_text = open('english.txt').read()
    spanish_text = open('spanish.txt').read()
    french_text = open('french.txt').read()
    russian_text = open('russian.txt', encoding='utf-8').read()

    return [
        (english_text, 'english'),
        (spanish_text, 'spanish'),
        (french_text, 'french'),
        (russian_text, 'russian')
    ]

# Предварительная обработка текста
def preprocess_text(text):
    # Приводим текст к нижнему регистру
    text = text.lower()

    # Удаляем пунктуацию
    text = text.translate(str.maketrans('', '', string.punctuation))
    return text

# Извлекаем признаки из текста с помощью TF-IDF
def extract_features(data):
    corpus = []
    labels = []

    for text, language in data:
        preprocessed_text = preprocess_text(text)
        corpus.append(preprocessed_text)
        labels.append(language)

    # Создаем объект TfidfVectorizer и преобразуем тексты в векторы признаков
    vectorizer = TfidfVectorizer()
    features = vectorizer.fit_transform(corpus)

    return features, labels, vectorizer


# Тренируем модель машинного обучения
def train_model(features, labels):
    # Разбиваем данные на обучающую и тестовую выборки
    X_train, X_test, y_train, y_test = train_test_split(features, labels, test_size=0.2)

    # Используем модель LinearSVC для классификации текста
    classifier = LinearSVC()
    classifier.fit(X_train, y_train)
    return classifier


# Определяем язык текста
def detect_language(text, classifier, vectorizer):
    preprocessed_text = preprocess_text(text)
    features = vectorizer.transform([preprocessed_text])
    language = classifier.predict(features)[0]

    return language


# Загружаем обучающий набор данных
data = load_data()

# Извлекаем признаки из текста
features, labels, vectorizer = extract_features(data)

# Тренируем модель
classifier = train_model(features, labels)

# Цикл для ввода текста пользователем
while True:
    # Получаем пользовательский ввод для текста
    test_text = input("Введите текст: ")

    # Определяем язык текста
    detected_language = detect_language(test_text, classifier, vectorizer)

    # Выводим результат
    print("Распознан язык:", detected_language)
