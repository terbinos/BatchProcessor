# BatchProcessor
to start build: run docker-compose up
1. login at http://localhost:9001/users/login 
    username: Terbinos
    password: admin123
2. start the job at http://localhost:9001/jobs/launch
3. view all jobs at http://localhost:9001/jobs/

Operations are asynchronous with RabbitMq message broker