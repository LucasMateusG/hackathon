import type { Repository } from "typeorm";
import type { UserEntity, PublicUser} from "../models/UserEntity";

export interface IUserRepository {
    getAllUsers(): Promise<PublicUser[]>;
    getUserById(id: number): Promise<UserEntity | undefined>;
    getUserByEmail(email: string): Promise<UserEntity | undefined>;
    createUser(data: Omit<UserEntity, "id">): Promise<PublicUser>;
    saveUser(entity: UserEntity): Promise<PublicUser>;
    deleteUser(id: number): Promise<boolean>;
};

function noPassword(u: UserEntity): PublicUser {
    const { password: _p, ...rest} = u;
    return rest;
}

export class UserRepository implements IUserRepository{
    constructor(private readonly repo: Repository<UserEntity>) {};

    async getAllUsers(): Promise<PublicUser[]>{
        const rows= await this.repo.find({order: {id: "ASC"}});
        return rows.map(noPassword);
    }

    async getUserById(id: number): Promise<UserEntity | undefined> {
        const row = await this.repo.findOne({where: { id }});
        return row ?? undefined;
    }

    async getUserByEmail(email: string): Promise<UserEntity | undefined> {
        const row = await this.repo.findOne({where: {email}});
        return row ?? undefined;
    }

    async createUser(data: Omit<UserEntity, "id">): Promise<PublicUser> {
        const dataIn = this.repo.create(data);
        const save = await this.repo.save(dataIn);
        return noPassword(save);
    }

    async saveUser(entity: UserEntity): Promise<PublicUser> {
        const save = await this.repo.save(entity);
        return noPassword(save);
    }

    async deleteUser(id: number): Promise<boolean> {
        const rm = await this.repo.delete(id);
        return (rm.affected ?? 0) > 0;
    }
}