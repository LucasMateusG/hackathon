import { z } from 'zod';

export const userSchema = z.object({
    name: z.string().min(6),
    email: z.string().email('Invalid email address'),
    password: z.string().min(6),
});

export type User = z.infer<typeof userSchema>;