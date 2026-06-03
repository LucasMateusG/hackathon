import { Request, Response, NextFunction } from 'express';
import { ZodError } from 'zod';
import { AppError } from '../utils/AppErrors';

export function errorMiddleware(
    err: unknown,
    req: Request,
    res: Response,
    next: NextFunction): void {
        if (err instanceof ZodError) {
            res.status(400).json({ error: 'Data validation failed', details: err.issues });
        } else if (err instanceof AppError) {
            res.status(err.statusCode).json({ error: err.message });
        } else {
            console.error(err);
            res.status(500).json({ error: 'Internal server error' });
        }
    }