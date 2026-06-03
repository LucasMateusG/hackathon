import type { Request, Response, NextFunction } from 'express';
import { z } from "zod";
import type { UserService } from '../services/UserService';
import AppError from '../utils/AppErrors';

export class UserController {
    constructor(private readonly userService: UserService) {};

    private schemaRegister = z.object({
        name: z.string({message: "Name is required"}),
        email: z.string().email({message: "Email invalido"}),
        password: z.string({message: "Senha é obrigatoria"}).min(6, "Minino 6 caracteres"),
    });

    private schemaUpdate = z.object({
        name: z.string().optional(),
        email: z.string().email().optional(),
        password: z.string().min(6).optional(),       
    }).refine((d) => d.name || d.email || d.password, {
        message: "Informe pelo menos um campo para atualizar",
        path: ["body"],
    });

    register = async (req: Request, res: Response, next: NextFunction) => {
        try {
            const input = this.schemaRegister.parse(req.body);
            const user = await this.userService.registerUser(input);
            res.status(201).json({ message: "Usuario cadastrado"});
        } catch (error) {
            next(error)
        }
    }

    listUser = async (_req: Request, res: Response, next: NextFunction) => {
        try {
            const users = await this.userService.listAllUsers();
            res.json({ users });
        } catch (error) {
            next(error)
        }
    }

    findUserById = async (req: Request, res: Response, next: NextFunction) => {
        try {
            const id = Number(req.params.id);
            if(!Number.isInteger(id) || id < 1) {
                throw new AppError(400, "Parametro errado");
            }
            const user = await this.userService.findUserById(id);
            res.json({ user });
        } catch (error) {
            next(error);
        }
    }

    updateUser = async (req: Request, res: Response, next: NextFunction) => {
        try {
            const id = Number(req.params.id);
            if (!Number.isInteger(id || id < 1)){
                throw new AppError(400, "Parametro Id invalido");
            }
            const data = this.schemaUpdate.parse(req.body);
            const userUpdtade = this.userService.update(id, data);
            res.json({
                message: "Usuario atulaizado com sucesso",
                userUpdtade,
            })
        } catch (error) {
            next(error)
        }
    }

    deleteUser = async (req: Request, res: Response, next: NextFunction) =>{
        try {
            const id = Number(req.params.id);
            if(!Number.isInteger(id) || id < 1){
                throw new AppError(400, "Parametros invalidos")
            }
            await this.userService.deleteUser(id);
            res.json({message: "Usuario deletado com sucesso"});
        } catch (error) {
            next(error);
        }
    }
}
