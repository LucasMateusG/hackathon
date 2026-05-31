import express from 'express';
import cors from 'cors';

const server = express();

server.use(cors({
    origin: 'http://localhost:80'
}))

// permite que o Node entenda JSON nativamente
server.use(express.json());

