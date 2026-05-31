import { Router } from 'express';
import z from 'zod';

const userRouter = Router();

userRouter.post('/users', (req, res) => {
    const userSchema = z.object({
        name: z.string(),
        email: z.string().email(),
        password: z.string().min(6)
    });
});