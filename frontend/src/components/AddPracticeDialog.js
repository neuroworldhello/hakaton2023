import React from "react";
import {MenuItem, Select, TextField} from "@mui/material";

export function AddPracticeDialog() {
    return (
        <div className='mt-4 w-full'>
            <div className='m-2'>
                <TextField label="Наименование практики" variant="outlined" />
            </div>
            <div className='m-2'>
                <Select displayEmpty value="" fullWidth margin="normal">
                    <MenuItem value="">Категория</MenuItem>
                    {/* Опции для категорий */}
                </Select>
            </div>
            <div className='m-2'>
                <Select displayEmpty value="" fullWidth margin="normal">
                    <MenuItem value="">Команда</MenuItem>
                    {/* Опции для команд */}
                </Select>
            </div>
            <div className='m-2'>
                <TextField label="Ссылка на документ" variant="outlined" />
            </div>
        </div>
    )
}