import { render, screen } from '@testing-library/react';
import ManagerPage from '../../pages/ManagerPage';
import axios from 'axios';

jest.mock('axios');

test('renders managers list', async () => {
  axios.get.mockResolvedValueOnce({ data: [{ id: 1, name: 'Alice', course: 'Java' }]});
  render(<ManagerPage back={() => {}} />);
  // wait for list
  const item = await screen.findByText(/Alice/);
  expect(item).toBeInTheDocument();
});
