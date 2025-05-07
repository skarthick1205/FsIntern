CREATE TABLE  IF NOT EXISTS post_feed (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT,
  caption VARCHAR(255),
  image_url VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO post_feed (user_id, caption, image_url, created_at) VALUES
(101, 'Sunset at the beach 🌅', 'https://cdn.example.com/img1.jpg', '2025-05-01 10:15:00'),
(102, 'Morning hike in the hills', 'https://cdn.example.com/img2.jpg', '2025-05-01 11:20:00'),
(103, 'Delicious brunch with friends 🥞', 'https://cdn.example.com/img3.jpg', '2025-05-01 12:30:00'),
(104, 'Throwback to last summer ☀️', 'https://cdn.example.com/img4.jpg', '2025-05-01 13:40:00'),
(105, 'New art project completed 🎨', 'https://cdn.example.com/img5.jpg', '2025-05-01 14:50:00'),
(106, 'Gym time 💪 #fitness', 'https://cdn.example.com/img6.jpg', '2025-05-01 15:05:00'),
(107, 'Homemade pizza night 🍕', 'https://cdn.example.com/img7.jpg', '2025-05-01 16:10:00'),
(108, 'City lights at night ✨', 'https://cdn.example.com/img8.jpg', '2025-05-01 17:20:00'),
(109, 'New book recommendations 📚', 'https://cdn.example.com/img9.jpg', '2025-05-01 18:30:00'),
(110, 'Exploring the old town 🏛️', 'https://cdn.example.com/img10.jpg', '2025-05-01 19:40:00');
