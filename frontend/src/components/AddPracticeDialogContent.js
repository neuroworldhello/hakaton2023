import React from "react";
import { FormControl, InputLabel, MenuItem, Select, TextField } from "@mui/material";
import {categories, teams} from "./consts";

export function AddPracticeDialogContent({practice, setPractice}) {
    const categoryOptions = categories.map(option => <MenuItem key={option} value={option}>{option}</MenuItem>);
    const teamOptions = teams.map(option => <MenuItem key={option} value={option}>{option}</MenuItem>);

    const handleNameChange = (event) => {
        setPractice({
            ...practice,
            name: event.target.value
        })
    }

    const handleCategoryChange = (event) => {
        setPractice({
            ...practice,
            category: event.target.value
        })
    }

    const handleTeamChange = (event) => {
        setPractice({
            ...practice,
            team: event.target.value
        })
    }

    const handleLinkChange = (event) => {
        setPractice({
            ...practice,
            documentLink: event.target.value
        })
    }

    return (
        <div className='mt-4 w-full'>
            <div className='m-2'>
                <TextField
                    required
                    value={practice.name || ''}
                    onChange={handleNameChange}
                    className='w-full'
                    label="Наименование практики"
                    variant="outlined" />
            </div>
            <div className='m-2'>
                <FormControl className="w-full">
                    <InputLabel id="category-label">Категория</InputLabel>
                    <Select
                        value={practice.category || ''}
                        labelId="category-label"
                        label="Категория"
                        fullWidth
                        margin="normal"
                        onChange={handleCategoryChange}
                    >
                        {categoryOptions}
                    </Select>
                </FormControl>
            </div>
            <div className='m-2'>
                <FormControl className="w-full">
                    <InputLabel id="team-label">Команда</InputLabel>
                    <Select
                        value={practice.team || ''}
                        labelId="team-label"
                        label="Команда"
                        fullWidth
                        margin="normal"
                        onChange={handleTeamChange}
                    >
                        {teamOptions}
                    </Select>
                </FormControl>
            </div>
            <div className='m-2'>
                <TextField
                    required
                    value={practice.documentLink || ''}
                    className='w-full'
                    label="Ссылка на документ"
                    variant="outlined"
                    onChange={handleLinkChange}
                />
            </div>
        </div>
    )
}