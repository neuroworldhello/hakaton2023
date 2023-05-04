import * as React from 'react';
import {
  AppBar,
  Toolbar,
  IconButton,
  Typography,
  Grid,
  TextField,
  Select,
  MenuItem,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Button,
} from '@mui/material';
import { Add } from '@mui/icons-material';
import {useState} from "react";
import {CustomDialog} from "./CustomDialog";
import {AddPracticeDialog} from "./AddPracticeDialog";

const TestData = [
  {
    id: 1,
    name: 'Практика 1',
    category: 'Категория 1',
    team: 'Команда 1',
    votes: 10,
    author: 'Автор 1',
  },
  {
    id: 2,
    name: 'Практика 2',
    category: 'Категория 2',
    team: 'Команда 2',
    votes: 8,
    author: 'Автор 2',
  },
  {
    id: 3,
    name: 'Практика 3',
    category: 'Категория 1',
    team: 'Команда 3',
    votes: 5,
    author: 'Автор 3',
  },
];

const categoryOptions = [
  'Все',
  'Frontend',
  'Backend',
  'DevOps',
  'Mobile',
  'Data Science',
];

function BestPractices() {
  const [category, setCategory] = React.useState('');
  const [team, setTeam] = React.useState('');
  const [search, setSearch] = React.useState('');


  const [cardDialogOpen, setCardDialogOpen] = useState(false);

  const handleCardDialogOpen = () => {
    setCardDialogOpen(true);
  }

  const handleCardDialogClose = () => {
    setCardDialogOpen(false);
  }

  function dialogContent () {
    return <AddPracticeDialog />
  }

  return (
    <div>
      <AppBar position="static">
        <Toolbar>
          <IconButton edge="start" color="inherit" aria-label="menu">
            {/* Логотип */}
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Наилучшие практики
          </Typography>
          <Button color="inherit">Войти</Button>
        </Toolbar>
      </AppBar>
      <img src={require('../logo.jpg')}  />
      {/* Кнопка добавления новой практики */}
      <Button
        variant="contained" sx={{ mt: 2 }}
        startIcon={<Add />}
        onClick={handleCardDialogOpen}
      >
        Добавить новую практику
      </Button>

      <Grid container sx={{ marginTop: 2 }}>
        <Grid item xs={12} sm={4} sx={{ pr: 2 }}>
          <TextField
            label="Поиск"
            fullWidth
            margin="normal"
            value={search}
            className="m-0"
            onChange={(e) => setSearch(e.target.value)}
          />
        </Grid>
        <Grid item xs={12} sm={4} sx={{ px: 2 }}>
          <Select
            displayEmpty
            value={category}
            onChange={(e) => setCategory(e.target.value)}
            fullWidth
            margin="normal"
          >
            {categoryOptions.map((option) => (
              <MenuItem key={option} value={option}>
                {option}
              </MenuItem>
            ))}
          </Select>
        </Grid>
        <Grid item xs={12} sm={4} sx={{ px: 2 }}>
          <Select
            displayEmpty
            value={team}
            onChange={(e) => setTeam(e.target.value)}
            fullWidth
            margin="normal"
          >
            {categoryOptions.map((option) => (
              <MenuItem key={option} value={option}>
                {option}
              </MenuItem>
            ))}
          </Select>
        </Grid>
      </Grid>

          {/* Таблица */}
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Наилучшие практики</TableCell>
                <TableCell>Категория</TableCell>
                <TableCell>Команда</TableCell>
                <TableCell>Количество голосов</TableCell>
                <TableCell>Автор</TableCell>
                <TableCell>Голосовать</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {TestData.map((item) => (
                <TableRow key={item.id}>
                  <TableCell>{item.name}</TableCell>
                  <TableCell>{item.category}</TableCell>
                  <TableCell>{item.team}</TableCell>
                  <TableCell>{item.votes}</TableCell>
                  <TableCell>{item.author}</TableCell>
                  <TableCell>
                    <Button variant="outlined" color="primary">
                      Голосовать
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
      <CustomDialog
        open={cardDialogOpen}
        onClose={handleCardDialogClose}
        content={dialogContent()}
      />
    </div>
  );
}

export default BestPractices;